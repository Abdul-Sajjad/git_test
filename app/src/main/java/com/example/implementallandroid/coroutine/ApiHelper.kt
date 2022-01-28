package com.example.implementallandroid.coroutine

class ApiHelper(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
    suspend fun getAllUsers() = apiService.getAllUsers()
}