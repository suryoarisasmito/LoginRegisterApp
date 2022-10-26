package com.example.loginappmvvm.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.loginappmvvm.R
import com.example.loginappmvvm.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnListener()
    }



    private fun btnListener() {

        binding.btnLogin.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.loginFragment)
        )
        binding.btnRegister.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.registerFragment)
        )
    }

}