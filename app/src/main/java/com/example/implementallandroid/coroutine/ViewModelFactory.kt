package com.example.implementallandroid.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(MainRepository(apiHelper)) as T
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }


}