package com.example.implementallandroid.dependencyinjection.dagger.apiwithdagger

import com.example.implementallandroid.coroutine.RetrofitBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiWithDIModule {

    @Singleton
    @Provides
    fun apiServiceDI(): ApiServiceDI {
        return Retrofit.Builder()
            .baseUrl(RetrofitBuilder.BASE_URL)
            .client(
                OkHttpClient.Builder().also{ client->
                    client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceDI::class.java)
    }
}