package com.example.implementallandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.implementallandroid.databinding.ActivitySimpleViewmodelLivedataBinding
import com.example.implementallandroid.simpleviewmodel.SimpleViewModelLiveDataExample

class SimpleViewModelLiveDataExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimpleViewmodelLivedataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySimpleViewmodelLivedataBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myViewModelLiveDataExample: SimpleViewModelLiveDataExample = ViewModelProvider(this@SimpleViewModelLiveDataExampleActivity).get(SimpleViewModelLiveDataExample::class.java)

        // We call it to change value to LiveData
        myViewModelLiveDataExample.setValueInLiveData("someValue")

        myViewModelLiveDataExample.someLiveData?.observe(this, Observer { value->
            binding.tvMainActivity.text = value
        })
    }
}