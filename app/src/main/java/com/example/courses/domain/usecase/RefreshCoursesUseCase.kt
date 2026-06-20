package com.example.courses.domain.usecase

import com.example.courses.domain.repository.CourseRepository

class RefreshCoursesUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke() = repository.refreshCourses()
}