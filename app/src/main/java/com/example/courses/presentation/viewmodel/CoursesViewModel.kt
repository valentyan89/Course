package com.example.courses.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.domain.model.Course
import com.example.courses.domain.repository.CourseRepository
import com.example.courses.domain.usecase.AddToFavouriteUseCase
import com.example.courses.domain.usecase.DeleteFromFavouriteUseCase
import com.example.courses.domain.usecase.GetCoursesUseCase
import com.example.courses.domain.usecase.GetFavouriteCoursesUseCase
import com.example.courses.domain.usecase.RefreshCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getFavouriteCoursesUseCase: GetFavouriteCoursesUseCase,
    private val refreshCoursesUseCase: RefreshCoursesUseCase
) : ViewModel() {
    private val _isSortedByDate = MutableStateFlow(false)
    val isSortedByDate = _isSortedByDate.asStateFlow()

    private val allCourses: StateFlow<List<Course>> = getCoursesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val mainCoursesState: StateFlow<List<Course>> = combine(
        allCourses,
        _isSortedByDate
    ) { courses, isSorted ->
        if (isSorted) courses.sortedByDescending { it.publishDate }
        else courses
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val favoriteCoursesState: StateFlow<List<Course>> = getFavouriteCoursesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleDateSorting() {
        _isSortedByDate.value = !_isSortedByDate.value
    }

    fun toggleLike(course: Course) {
        viewModelScope.launch {
            addToFavouriteUseCase(course)
        }
    }
}