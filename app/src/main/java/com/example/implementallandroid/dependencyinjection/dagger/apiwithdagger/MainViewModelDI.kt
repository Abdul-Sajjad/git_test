package com.example.implementallandroid.dependencyinjection.dagger.apiwithdagger

import androidx.lifecycle.*
import com.example.implementallandroid.coroutine.Resource
import com.example.implementallandroid.coroutine.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModelDI @Inject constructor(private val mainRepository: MainRepositoryDI) : ViewModel() {

    fun first() = GlobalScope.launch(Dispatchers.IO) {

    }
    suspend fun fetchUsers(): Resource<List<User>> {
        return viewModelScope.async(Dispatchers.IO) {
            Resource.loading(data = null)
            try {
                Resource.success(data = mainRepository.getUsers())
            }catch (exception: Exception) {
                Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }.await()
    }



    private var _usersList: MutableLiveData<Resource<List<User>>> = MutableLiveData()
    var usersList: LiveData<Resource<List<User>>> = _usersList

    fun findUsers() = viewModelScope.launch {
            _usersList.postValue(Resource.loading(data = null))
            try {
                _usersList.postValue(Resource.success(data = mainRepository.getUsers()))
            }catch (exception: Exception) {
                _usersList.postValue(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }

//    fun getUsers() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = mainRepository.getUsers()))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }
}