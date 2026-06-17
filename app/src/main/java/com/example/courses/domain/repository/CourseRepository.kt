package com.example.courses.domain.repository

import com.example.courses.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun getCourses() : List<Course>
    fun getFavouriteCourses(): Flow<List<Course>>
    suspend fun addToFavourite(course: Course)
    suspend fun deleteFromFavourites(course: Course)
}