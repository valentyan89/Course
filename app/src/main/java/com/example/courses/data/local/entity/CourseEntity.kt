package com.example.courses.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteCourses")
data class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Double,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)