package com.example.courses.data.mapper

import com.example.courses.data.local.entity.CourseEntity
import com.example.courses.data.model.CourseDto
import com.example.courses.domain.model.Course

fun CourseDto.toDomain() : Course {
    return Course(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price.toInt(),
        rate = this.rate.toDouble(),
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}

fun CourseEntity.toDomain(): Course {
    return Course(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = true,
        publishDate = this.publishDate
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
        publishDate = this.publishDate
    )
}