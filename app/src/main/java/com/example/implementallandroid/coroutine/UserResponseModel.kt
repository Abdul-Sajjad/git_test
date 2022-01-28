package com.example.implementallandroid.coroutine

import com.google.gson.annotations.SerializedName

class UserResponseModel(@SerializedName("data") val data: List<User>) {
}