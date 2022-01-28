package com.example.implementallandroid.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(loginTableModelTableModel: EntityLoginTableModel)

    @Query("SELECT * FROM Login WHERE Username =:username")
    fun getLoginDetails(username: String?) : LiveData<EntityLoginTableModel>

    @Query("SELECT * FROM Login")
    fun getAllLoginDetails(): LiveData<List<EntityLoginTableModel>>

//    @Query("DELETE FROM Login WHERE id =:id")
//    suspend fun removeDetailItem(id: Int)

    @Delete
    suspend fun removeDetailItem(loginTableModelTableModel: EntityLoginTableModel)

    @Update
    suspend fun updateDetailItem(loginTableModelTableModel: EntityLoginTableModel)

}