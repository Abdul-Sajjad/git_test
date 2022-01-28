package com.example.implementallandroid.coroutine

class MainRepository (private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
    suspend fun getAllUsers() = apiHelper.getAllUsers()
}