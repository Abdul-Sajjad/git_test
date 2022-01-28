package com.example.implementallandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.implementallandroid.R
import com.example.implementallandroid.databinding.ActivityNavigationComponentExampleBinding

class NavigationComponentExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationComponentExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationComponentExampleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        //Initialise NavHostFragment
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
//
//        val navController = navHostFragment.findNavController()
    }
}