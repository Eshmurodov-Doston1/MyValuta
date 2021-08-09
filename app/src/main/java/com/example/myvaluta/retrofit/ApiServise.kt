package com.example.myvaluta.retrofit

import com.example.myvaluta.models.Course
import retrofit2.http.GET

interface ApiServise {
    @GET(".")
    suspend fun getCourse():List<Course>
}