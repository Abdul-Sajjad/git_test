package com.example.implementallandroid.dependencyinjection.dagger.apiwithdagger

import com.example.implementallandroid.activity.DaggerWithDiActivity
import com.example.implementallandroid.coroutine.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiWithDIModule::class])
interface ApiWithDIComponent {
    fun inject(app: DaggerWithDiActivity)
    fun viewModelsFactory(): ViewModelFactoryDI

}