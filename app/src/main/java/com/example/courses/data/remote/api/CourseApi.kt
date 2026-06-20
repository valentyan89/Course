package com.example.courses.data.remote.api

import com.example.courses.data.remote.response.CoursesResponse
import retrofit2.http.GET

interface CourseApi {
    @GET("valentyan89/eea539b29eb9dcf5aaa0ca24bed6bd79/raw/d34bff865aba4901a76e49f892c3448374ad0386/courses.json")
    suspend fun getCourses(): CoursesResponse
}