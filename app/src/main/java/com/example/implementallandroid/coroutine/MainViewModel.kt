package com.example.implementallandroid.coroutine

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    lateinit var job: Job
    fun first() = GlobalScope.launch(Dispatchers.IO) {

    }

    suspend fun fetchUsers(): Resource<List<User>> {
        return viewModelScope.async(Dispatchers.IO) {
            Resource.loading(data = null)
            try {
                Resource.success(data = mainRepository.getUsers())
            } catch (exception: Exception) {
                Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }.await()
    }


    private var _usersList: MutableLiveData<Resource<List<User>>> = MutableLiveData()
    private var _usersList2: MutableLiveData<Resource<UserResponseModel>> = MutableLiveData()

    var usersList: LiveData<Resource<List<User>>> = _usersList
    var usersList2: LiveData<Resource<UserResponseModel>> = _usersList2

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _usersList.postValue(Resource.error(data = null, message = throwable.message
                ?: "Error Occurred!"))
    }

    fun findUsers3() {
        job = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _usersList.postValue(Resource.loading(data = null))
            _usersList.postValue(Resource.success(data = mainRepository.getUsers()))
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun findUsers() = viewModelScope.launch {

//        try {
//            if (hasInternetConnection()) {
//                val response = driverRepository.getAssignedPlateNumbersResponse()
//                vehicleNumberPlateLiveData.postValue(handlePlateNumberResponse(response))
//            } else {
//                vehicleNumberPlateLiveData.postValue(Resource.Error("No internet connection"))
//            }
//        } catch (t: Throwable) {
//            t.printStackTrace()
//            when (t) {
//                is IOException -> vehicleNumberPlateLiveData.postValue(Resource.Error("Network Failure"))
//                else -> vehicleNumberPlateLiveData.postValue(Resource.Error("Conversion Error"))
//            }
//        }
        _usersList.postValue(Resource.loading(data = null))
        try {
            _usersList.postValue(Resource.success(data = mainRepository.getUsers()))
            _usersList2.postValue(Resource.success(data = mainRepository.getAllUsers()))
        } catch (exception: Exception) {
            _usersList.postValue(
                    Resource.error(
                            data = null,
                            message = exception.message ?: "Error Occurred!"
                    )
            )
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
    //----------------------------------------------------------------------------------------------------------------------
    fun example() {
        viewModelScope.launch {
            Log.d("ViewModel", "Just viewModelScope: ${Thread.currentThread().name}")
        }
        // Output: Just viewModelScope: main

        viewModelScope.launch(Dispatchers.IO) {
            Log.d("ViewModel", "IO viewModelScope: ${Thread.currentThread().name}")
        }
        // Output: IO viewModelScope: DefaultDispatcher-worker-3

        viewModelScope.launch {
            Log.d("ViewModel", "viewModelScope thread: ${Thread.currentThread().name}")
            withContext(Dispatchers.IO) {
                delay(3000)
                Log.d("ViewModel", "withContext thread: ${Thread.currentThread().name}")
            }
            Log.d("ViewModel", "I'm finished!")
        }
        //    Output:
        //    viewModelScope thread: main
        //            withContext thread: DefaultDispatcher-worker-4

    }


//----------------------------------------------------------------------------------------------------------------------
}


//suspend fun fetchData(cash: Boolean, type: Type, requestFactory: BaseRequestFactory): Any? {
//    var errorMessage: String? = null;
//    val response = try {
//        val isNetworkConnected = NetworkUtil.isNetworkAvailable()
//        if (isNetworkConnected) {
//            remoteRepo.fetchData(requestFactory)
//        } else {
//            errorMessage = "Check your internet Connection"
//            null
//        }
//    } catch (ex: Exception) {
//        errorMessage = "Something went wrong."
//        ex.printStackTrace()
//        null
//    }
//    return if (response != null && response.isSuccessful) {
//        if (cash) {
//            localRepo.saveObject(response.body(), type)
//        }
//        response.body()
//    } else if (response != null && response.code() == 401) {
//        return response
//    } else if (cash) {
//        localRepo.getCashedObject(type)
//    } else {
//        var message = errorMessage ?: "Server Error"
//        var responseCode = ""
//        val errorBody = response?.errorBody()?.string()
//        if (errorBody != null) {
//            try {
//                val model: BaseModel = errorBody.convertToModel(BaseModel::class.java)
//                responseCode = model.responseCode.toString()
//                if (isUrduEnabled()){
//                    message = model.responseMessageUr!!
//                } else {
//                    message = model.responseMessageEn!!
//                }
//            } catch (e: Exception) {
//                Logger.e("Response Failure", "Empty Response")
//            }
//        }
//        if(responseCode.isNullOrEmpty()) throw Exception(message)
//        else throw ResponseException(message, responseCode)
//    }
//}
