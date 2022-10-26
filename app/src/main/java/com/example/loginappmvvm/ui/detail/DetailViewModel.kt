package com.example.loginappmvvm.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginappmvvm.repository.RegisterRepository

class DetailViewModel(
    private val repository: RegisterRepository,
    application: Application
) : AndroidViewModel(application) {

    val users = repository.users
    init {
        Log.i("MYTAG", "inside_user_list_init")
    }

    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo: LiveData<Boolean>
        get() = _navigateTo

    fun doneNavigating(){
        _navigateTo.value = false
    }

    fun logoutButtonClicked(){
        _navigateTo.value = true
    }
}