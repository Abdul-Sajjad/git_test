package com.example.implementallandroid.dependencyinjection.dagger

import com.example.implementallandroid.activity.DependencyInjectionActivityExample
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPrefModule::class])
interface DIComponent {
    fun inject(activity: DependencyInjectionActivityExample)
}