package com.example.courses.domain.usecase

import com.example.courses.domain.model.Course
import com.example.courses.domain.repository.CourseRepository

class AddToFavouriteUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(course: Course) = repository.addToFavourite(course)
}