package com.example.implementallandroid.navigationfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.implementallandroid.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    val safeArgs: SecondFragmentArgs by navArgs()

    private lateinit var fragmentSecondBinding: FragmentSecondBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        val binding = FragmentSecondBinding.inflate(inflater, container, false)
        fragmentSecondBinding = binding

        val arg1 = safeArgs.arg1
        val arg2 = safeArgs.arg2

        binding.tvArgument1.text = arg1.toString()
        binding.tvArgument2.text = arg2

        return binding.root
    }
}