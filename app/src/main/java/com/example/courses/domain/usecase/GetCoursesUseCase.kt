package com.example.courses.domain.usecase

import com.example.courses.domain.model.Course
import com.example.courses.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCoursesUseCase(private val repository: CourseRepository) {
    operator fun invoke(): Flow<List<Course>> = repository.getCourses()
}