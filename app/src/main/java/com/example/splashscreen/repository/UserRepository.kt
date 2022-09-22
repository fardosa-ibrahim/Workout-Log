package com.example.splashscreen.repository

import com.example.splashscreen.api.ApiClient
import com.example.splashscreen.api.ApiInterface
import com.example.splashscreen.models.RegisterRequest
import com.example.splashscreen.models.loginRequest
import com.example.splashscreen.models.loginResponce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiCient= ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: loginRequest):Response<loginResponce>
    = withContext(Dispatchers.IO){
        val responce=apiCient.loginUser(loginRequest)
        return@withContext responce
    }
    suspend fun registerUser(RegisterRequest: RegisterRequest)
    = withContext(Dispatchers.IO){
        val responce=apiCient.RegisterUser(RegisterRequest)
        return@withContext responce
    }
}

