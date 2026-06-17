package com.example.courses.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.courses.data.local.dao.CourseDao
import com.example.courses.data.local.entity.CourseEntity

@Database(
    entities = [CourseEntity::class],
    version = 2,
    exportSchema = true
)
abstract class CourseDataBase : RoomDatabase() {
    abstract fun courseDatabase(): CourseDao
}