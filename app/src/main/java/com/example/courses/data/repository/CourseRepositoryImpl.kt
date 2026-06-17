package com.example.courses.data.repository

import com.example.courses.data.local.dao.CourseDao
import com.example.courses.data.mapper.toDomain
import com.example.courses.data.mapper.toEntity
import com.example.courses.domain.model.Course
import com.example.courses.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class CourseRepositoryImpl @Inject constructor(private val courseDao: CourseDao) : CourseRepository {
    override suspend fun getCourses(): List<Course> {
        TODO("Not yet implemented")
    }

    override fun getFavouriteCourses(): Flow<List<Course>> {
        return courseDao.getFavouriteCourses().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToFavourite(course: Course) {
        courseDao.insertCourse(course.toEntity())
    }

    override suspend fun deleteFromFavourites(course: Course) {
        courseDao.deleteCourse(course.toEntity())
    }
}