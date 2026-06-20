package com.example.courses.domain.usecase

import com.example.courses.domain.model.Course
import com.example.courses.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCoursesUseCase(private val repository: CourseRepository) {
    operator fun invoke(): Flow<List<Course>> {
        return repository.getIds().map { favouriteIds ->
            repository.getCourses().map { course ->
                course.copy(hasLike = favouriteIds.contains(course.id))
            }
        }
    }
}