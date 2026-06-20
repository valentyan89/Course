package com.example.courses.data.remote.api

import com.example.courses.data.remote.response.CoursesResponse
import retrofit2.http.GET

interface CourseApi {
    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hq-BbS8-q&export=download")
    suspend fun getCourses(): CoursesResponse
}