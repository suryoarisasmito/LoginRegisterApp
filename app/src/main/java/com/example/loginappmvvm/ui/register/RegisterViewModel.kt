package com.example.loginappmvvm.ui.register

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginappmvvm.database.RegisterEntity
import com.example.loginappmvvm.repository.RegisterRepository
import kotlinx.coroutines.*

class RegisterViewModel(
    private val repository: RegisterRepository,
    application: Application
) : AndroidViewModel(application), Observable {

    init {
        Log.i("MYTAG", "Init")
    }

    private var userdata: String? = null

    var userDetailsLiveData = MutableLiveData<Array<RegisterEntity>>()

    @Bindable
    val fullName = MutableLiveData<String>()

    @Bindable
    val email = MutableLiveData<String>()

    @Bindable
    val password = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo: LiveData<Boolean>
        get() = _navigateTo

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastEmail = MutableLiveData<Boolean>()

    val errorToastEmail: LiveData<Boolean>
        get() = _errorToastEmail


    fun submitButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (fullName.value == null || email.value == null || password.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                withContext(Dispatchers.Main) {
                    val emails = repository.getEmail(email.value!!)
                    Log.i("MYTAG", "${emails.toString()}-------------")
                    if (emails != null) {
                        _errorToastEmail.value = true
                        Log.i("MYTAG", "Inside If Not Null")
                    } else {
                        Log.i("MYTAG", "${userDetailsLiveData.value.toString()} ASDFASDFASDFASDF")
                        Log.i("MYTAG", "OK I'm in")
                        val sFullName = fullName.value!!
                        val sEmail = email.value!!
                        val sPassword = password.value!!
                        Log.i("MYTAG", "Inside Submit")
                        insert(RegisterEntity(0, sFullName, sEmail, sPassword))
                        fullName.value = null
                        email.value = null
                        password.value = null
                        _navigateTo.value = true
                    }
                }
            }
        }
    }

    fun doneNavigating() {
        _navigateTo.value = false
        Log.i("MYTAG", "Done navigating ")
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastEmail() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting  Email")
    }

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}