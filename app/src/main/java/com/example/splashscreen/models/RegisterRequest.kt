package com.example.splashscreen.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("first_name") var FirstName:String,
    @SerializedName("last_name") var LastName:String,
    @SerializedName("phone_number") var PhoneNumber:String,
    var email:String,
    var password:String
)
