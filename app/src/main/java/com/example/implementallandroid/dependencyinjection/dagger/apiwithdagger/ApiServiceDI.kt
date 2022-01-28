package com.example.implementallandroid.dependencyinjection.dagger.apiwithdagger

import com.example.implementallandroid.coroutine.User
import retrofit2.http.GET

interface ApiServiceDI {
    @GET("users")
    suspend fun getUsers(): List<User>
}

