package com.example.implementallandroid.dependencyinjection.dagger.apiwithdagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactoryDI @Inject constructor(private val apiServiceDI: ApiServiceDI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModelDI::class.java) -> MainViewModelDI(MainRepositoryDI(apiServiceDI)) as T
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}