package com.example.implementallandroid.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.implementallandroid.coroutine.*
import com.example.implementallandroid.databinding.ActivityDaggerWithDiBinding
import com.example.implementallandroid.dependencyinjection.dagger.apiwithdagger.DaggerApiWithDIComponent
import com.example.implementallandroid.dependencyinjection.dagger.apiwithdagger.MainViewModelDI
import com.example.implementallandroid.dependencyinjection.dagger.apiwithdagger.ViewModelFactoryDI
import javax.inject.Inject


class DaggerWithDiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDaggerWithDiBinding

    lateinit var viewModelDI: MainViewModelDI

    @Inject
    lateinit var viewModelFactoryDI: ViewModelFactoryDI

//    private val viewModelDI: MainViewModelDI by viewModels {
//        DaggerApiWithDIComponent.create().viewModelsFactory()
//    }
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDaggerWithDiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        DaggerApiWithDIComponent.create().inject(this)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModelDI = ViewModelProvider(this, viewModelFactoryDI).get(MainViewModelDI::class.java)
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }
    private fun setupObservers() {
        viewModelDI.findUsers()
        viewModelDI.usersList.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }
    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}