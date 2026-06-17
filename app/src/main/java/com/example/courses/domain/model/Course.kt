package com.example.courses.domain.model

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Double,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)