package com.example.courses.domain.repository

import com.example.courses.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getCourses() : Flow<List<Course>>
    fun getFavouriteCourses(): Flow<List<Course>>
    suspend fun addToFavourite(course: Course)
    suspend fun deleteFromFavourites(course: Course)
    suspend fun refreshCourses()
}