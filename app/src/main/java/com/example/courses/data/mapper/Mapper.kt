package com.example.courses.data.mapper

import com.example.courses.data.local.entity.CourseEntity
import com.example.courses.data.model.CourseDto
import com.example.courses.domain.model.Course
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val jsonDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

private val uiDateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))

private fun formatToUiDate(rawDate: String): String {
    val date = LocalDate.parse(rawDate, jsonDateFormatter)
    val parts = date.format(uiDateFormatter).split(" ")

    val capitalizedMonth = parts[1].replaceFirstChar { it.titlecase(Locale("ru")) }

    return "${parts[0]} $capitalizedMonth ${parts[2]}"
}

fun CourseDto.toDomain(isFavourite: Boolean) : Course {
    val parsedDate = LocalDate.parse(this.publishDate, jsonDateFormatter)

    return Course(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price.replace(" ", "").toInt(),
        rate = this.rate.toDouble(),
        startDate = formatToUiDate(this.startDate),
        hasLike = isFavourite,
        publishDate = parsedDate
    )
}

fun CourseEntity.toDomain(): Course {
    val parsedDate = LocalDate.parse(this.publishDate, jsonDateFormatter)

    return Course(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = formatToUiDate(this.startDate),
        hasLike = true,
        publishDate = parsedDate
    )
}

fun Course.toEntity(): CourseEntity {
    return CourseEntity(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate.format(jsonDateFormatter)
    )
}