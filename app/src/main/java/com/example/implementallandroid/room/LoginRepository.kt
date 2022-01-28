package com.example.implementallandroid.room

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class LoginRepository {

    companion object {

        var loginDatabase: LoginDatabase? = null

        var loginTableModelTableModel: LiveData<EntityLoginTableModel>? = null
        var loginTableModelTableList: LiveData<List<EntityLoginTableModel>>? = null

        fun initializeDB(context: Context) : LoginDatabase {
            return LoginDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {

            loginDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = EntityLoginTableModel(username, password)
                loginDatabase?.loginDao()?.insertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, username: String) : LiveData<EntityLoginTableModel>? {

            loginDatabase = initializeDB(context)
            loginTableModelTableModel = loginDatabase?.loginDao()?.getLoginDetails(username)
            return loginTableModelTableModel
        }

        fun getAllLoginDetails(context: Context) : LiveData<List<EntityLoginTableModel>>? {


            loginDatabase = initializeDB(context)

            loginTableModelTableList = loginDatabase?.loginDao()?.getAllLoginDetails()

            return loginTableModelTableList
        }

        fun remoteDetailItem(loginTableModelTableModel: Any){
            CoroutineScope(IO).launch {
                loginDatabase?.loginDao()?.removeDetailItem(loginTableModelTableModel as EntityLoginTableModel)
            }
        }

        fun updateDetailItem(loginTableModelTableModel: EntityLoginTableModel){
            CoroutineScope(IO).launch {
                loginDatabase?.loginDao()?.updateDetailItem(loginTableModelTableModel)
            }
        }

    }
}