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

class UserViewModel:ViewModel() {
    val userRepository=UserRepository()
    val loginResponseLiveData=MutableLiveData<loginResponce>()
    val errorLiveData=MutableLiveData<String>()
    var registerResponseLiveData=MutableLiveData<RegisterResponse>()
    val registerErrorLiveData= MutableLiveData<String?>()

    fun loginUser(loginRequest: loginRequest){
        viewModelScope.launch {
            val responce=userRepository.loginUser(loginRequest)
            if(responce.isSuccessful){
                loginResponseLiveData.postValue(responce.body())
            }
            else{
                errorLiveData.postValue(responce.errorBody()?.string())
            }
        }
    }
    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                registerResponseLiveData.postValue(response.body())
            }
            else{
                val error=response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }




    }