package com.example.splashscreen.api

import com.example.splashscreen.models.RegisterRequest
import com.example.splashscreen.models.RegisterResponse
import com.example.splashscreen.models.loginRequest
import com.example.splashscreen.models.loginResponce
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>
    @POST("/login")
    fun loginUser(@Body loginRequest:loginRequest):Call<loginResponce>
}
