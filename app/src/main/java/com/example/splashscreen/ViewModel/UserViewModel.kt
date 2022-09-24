package com.example.splashscreen.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splashscreen.models.RegisterRequest
import com.example.splashscreen.models.RegisterResponse
import com.example.splashscreen.models.loginRequest
import com.example.splashscreen.models.loginResponce
import com.example.splashscreen.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class UserViewModel:ViewModel() {
    val userRepository = UserRepository()
    val loginResponseLiveData = MutableLiveData<loginResponce>()
    val errorLiveData = MutableLiveData<String>()
    var registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String?>()

    fun loginUser(loginRequest: loginRequest) {
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful) {
                loginResponseLiveData.postValue(response.body())
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun registerUser(RegisterRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepository.registerUser(RegisterRequest)
            if (response.isSuccessful) {
                registerResponseLiveData.postValue(response.body())
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }

        }
    }
}




