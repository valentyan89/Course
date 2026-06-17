package com.example.courses.domain.usecase

import com.example.courses.domain.model.Course
import com.example.courses.domain.repository.CourseRepository

class DeleteFromFavouriteUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(course: Course) = repository.deleteFromFavourites(course)
}