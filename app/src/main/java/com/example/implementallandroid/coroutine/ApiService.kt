package com.example.implementallandroid.coroutine

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users")
    suspend fun getAllUsers(): UserResponseModel

}