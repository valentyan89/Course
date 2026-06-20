package com.example.courses.data.remote.response

import com.example.courses.data.model.CourseDto
import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("courses") val courses: List<CourseDto>
)
