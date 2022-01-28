package com.example.implementallandroid.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.implementallandroid.databinding.ActivityDependencyInjectionExampleBinding
import com.example.implementallandroid.dependencyinjection.dagger.DIComponent
import com.example.implementallandroid.dependencyinjection.dagger.DaggerDIComponent
import com.example.implementallandroid.dependencyinjection.dagger.SharedPrefModule
import javax.inject.Inject


class DependencyInjectionActivityExample : AppCompatActivity() {
    private lateinit var binding: ActivityDependencyInjectionExampleBinding
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var diComponent: DIComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDependencyInjectionExampleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeDaggerComponent()
        saveAndRetrieve()
    }

    private fun saveAndRetrieve() {
        binding.saveBtn.setOnClickListener {
            saveToSharedPreference()
        }

        binding.retriveBtn.setOnClickListener {
            retrieveFromSharedPreferences()
        }
    }

    private fun saveToSharedPreference() {
        val editor = sharedPreferences.edit()
        editor.putString("username", "Waqas")
        editor.putString("number", "12345")
        editor.apply()
    }

    private fun retrieveFromSharedPreferences() {
        binding.tvName.text = sharedPreferences.getString("username", "Default")
        binding.tvNum.text = sharedPreferences.getString("number", "12345")
    }

    private fun initializeDaggerComponent() {
        diComponent = DaggerDIComponent
            .builder()
            .sharedPrefModule(SharedPrefModule(this))
            .build()
        diComponent.inject(this)
    }
}