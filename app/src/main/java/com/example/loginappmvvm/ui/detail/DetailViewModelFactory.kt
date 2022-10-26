package com.example.loginappmvvm.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginappmvvm.repository.RegisterRepository
import com.example.loginappmvvm.ui.register.RegisterViewModel
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val repository: RegisterRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class ${modelClass.name}")
    }
}