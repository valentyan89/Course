package com.example.courses.domain.model

import java.time.LocalDate

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Double,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: LocalDate
)