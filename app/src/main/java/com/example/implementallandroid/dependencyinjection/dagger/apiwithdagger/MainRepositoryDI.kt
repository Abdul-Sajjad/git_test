package com.example.implementallandroid.dependencyinjection.dagger.apiwithdagger

import javax.inject.Inject

class MainRepositoryDI @Inject constructor(private val apiServiceDI: ApiServiceDI) {
    suspend fun getUsers() = apiServiceDI.getUsers()
}