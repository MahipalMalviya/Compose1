package com.mahipal.interviewtest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/api/inspections/submit")
    fun submitInspection(@Body postId: Any): Call<Any>

    @GET("/api/inspections/start")
    fun startInspection(): Call<Any>

    @POST("/api/login")
    fun login(@Body request: Any): Call<Any>
}