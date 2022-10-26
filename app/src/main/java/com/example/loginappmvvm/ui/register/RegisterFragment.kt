package com.example.loginappmvvm.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.loginappmvvm.R
import com.example.loginappmvvm.database.RegisterDatabase
import com.example.loginappmvvm.databinding.FragmentRegisterBinding
import com.example.loginappmvvm.repository.RegisterRepository

class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register, container, false
        )
        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDao

        val repository = RegisterRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)

        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)

        binding.myRegisterViewModel = registerViewModel

        binding.lifecycleOwner = this

        registerViewModel.navigateTo.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        })

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("MYTAG", it.toString() + "000000000000000000000000")
        })

        registerViewModel.errorToast.observe(viewLifecycleOwner, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoast()
            }
        })

        registerViewModel.errorToastEmail.observe(viewLifecycleOwner, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoastEmail()
            }
        })

        return binding.root
    }

    private fun displayUsersList() {
        Log.i("MYTAG", "indisplayUserList")
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

}