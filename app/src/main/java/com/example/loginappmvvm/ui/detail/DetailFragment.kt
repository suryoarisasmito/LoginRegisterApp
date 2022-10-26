package com.example.loginappmvvm.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginappmvvm.R
import com.example.loginappmvvm.database.RegisterDatabase
import com.example.loginappmvvm.databinding.FragmentDetailBinding
import com.example.loginappmvvm.repository.RegisterRepository

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDao

        val repository = RegisterRepository(dao)

        val factory = DetailViewModelFactory(repository, application)

        detailViewModel =
            ViewModelProvider(this, factory).get(DetailViewModel::class.java)

        binding.userDetailLayout = detailViewModel

        binding.lifecycleOwner = this

        detailViewModel.navigateTo.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                val action = DetailFragmentDirections.actionDetailFragmentToLoginFragment()
                NavHostFragment.findNavController(this).navigate(action)
                detailViewModel.doneNavigating()
            }
        })

        initRecyclerView()

        // Inflate the layout for this fragment
        return binding.root
    }


    private fun initRecyclerView() {
        binding.rvDetail.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }


    private fun displayUsersList() {
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        detailViewModel.users.observe(viewLifecycleOwner, Observer {
            binding.rvDetail.adapter = DetailAdapter(it)
        })

    }


}