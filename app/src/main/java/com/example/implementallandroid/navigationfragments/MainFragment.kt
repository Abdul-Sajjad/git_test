package com.example.implementallandroid.navigationfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.implementallandroid.R
import com.example.implementallandroid.databinding.FragmentMainBinding
import com.example.implementallandroid.room.LoginViewModel

class MainFragment : Fragment() {

    private lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var fragmentViewModelExample: FragmentViewModelExample

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val binding = FragmentMainBinding.inflate(inflater, container, false)
        fragmentMainBinding = binding
        fragmentViewModelExample = ViewModelProvider(this).get(FragmentViewModelExample::class.java)
        fragmentViewModelExample.someLiveData?.observe(viewLifecycleOwner, Observer { value->
        })


        //using destination id
        binding.tvSwitchF1.setOnClickListener { button->
            findNavController().navigate(R.id.firstFragment)
        }

        binding.tvSwitchF2.setOnClickListener { button->
            val action = MainFragmentDirections.actionMainToSecondFragment(arg1 = 1234, arg2 = "abcd")
            button.findNavController().navigate(action)
        }
        //using action
//        binding.tvMain.setOnClickListener { button->
//            button.findNavController().navigate(R.id.action_main_to_firstFragment)
//        }

        // using click listener
//        binding.tvMain.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.firstFragment, null))


        return binding.root
    }
}