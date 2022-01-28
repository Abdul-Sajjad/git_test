package com.example.implementallandroid.simpleviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimpleViewModelLiveDataExample : ViewModel() {
    var someLiveData: LiveData<String>? = MutableLiveData()

    fun setValueInLiveData(someValue: String) {
        // save by main thread
        (someLiveData as MutableLiveData).value = someValue
        // save by background thread
        //(someLiveData as? MutableLiveData)?.value = someValue
    }
}