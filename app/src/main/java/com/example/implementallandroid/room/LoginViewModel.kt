package com.example.implementallandroid.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var liveDataLoginTableModel: LiveData<EntityLoginTableModel>? = null
    var liveDataLoginTableList: LiveData<List<EntityLoginTableModel>>? = null

    fun insertData(context: Context, username: String, password: String) {
        LoginRepository.insertData(context, username, password)
    }

    fun getLoginDetails(context: Context, username: String) : LiveData<EntityLoginTableModel>? {
        liveDataLoginTableModel = LoginRepository.getLoginDetails(context, username)
        return liveDataLoginTableModel
    }


    fun getAllLoginDetails(context: Context) {
        liveDataLoginTableList = LoginRepository.getAllLoginDetails(context)

        viewModelScope.launch(Dispatchers.IO) {
        }
    }

    fun removeDetailItem(context: Context,id:Any){
        LoginRepository.remoteDetailItem(loginTableModelTableModel = id)
    }

    fun updateDetailItem(context: Context,loginTableModelTableModel:EntityLoginTableModel){
        LoginRepository.updateDetailItem(loginTableModelTableModel = loginTableModelTableModel)
    }

}