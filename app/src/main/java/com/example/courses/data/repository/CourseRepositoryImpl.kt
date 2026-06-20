package com.example.courses.data.repository

import com.example.courses.data.local.dao.CourseDao
import com.example.courses.data.mapper.toDomain
import com.example.courses.data.mapper.toEntity
import com.example.courses.data.model.CourseDto
import com.example.courses.data.remote.api.CourseApi
import com.example.courses.domain.model.Course
import com.example.courses.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class CourseRepositoryImpl @Inject constructor(
    private val courseDao: CourseDao,
    private val courseApi: CourseApi
) : CourseRepository {
     override suspend fun getCourses(): List<Course> {
        return courseApi.getCourses().courses.map { it.toDomain(false) }
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

    override fun getIds(): Flow<List<Int>> {
        return courseDao.getFavouritesIds()
    }
}