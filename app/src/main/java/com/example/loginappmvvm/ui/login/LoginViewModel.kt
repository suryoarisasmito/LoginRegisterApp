package com.example.loginappmvvm.ui.login

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginappmvvm.repository.RegisterRepository
import kotlinx.coroutines.*

class LoginViewModel(
    private val repository: RegisterRepository,
    application: Application
) : AndroidViewModel(application), Observable {

    val users = repository.users


    // memungkinkan ViewModel untuk Mengakses data secara langsung dari Layout selama Run Time.
    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _navigateToRegister = MutableLiveData<Boolean>()

    val navigateToRegister: LiveData<Boolean>
        get() = _navigateToRegister

    private val _navigateToUserDetails = MutableLiveData<Boolean>()

    val navigateToUserDetails: LiveData<Boolean>
        get() = _navigateToUserDetails

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastEmail = MutableLiveData<Boolean>()

    val errorToastEmail: LiveData<Boolean>
        get() = _errorToastEmail

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword

    fun sigUp(){
        _navigateToRegister.value = true
    }

    // Function Triggered when the Login Button is Clicked, Via Binding
    fun loginButton(){
        if (inputEmail.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                withContext(Dispatchers.Main) {
                    val emails = repository.getEmail(inputEmail.value!!)
                    Log.i("MYTAG", "${emails.toString()}-------------")
                    if (emails != null) {
                        if (emails.password == inputPassword.value) {
                            inputEmail.value = null
                            inputPassword.value = null
                            _navigateToUserDetails.value = true
                        } else {
                            _errorToastInvalidPassword.value = true
                        }
                    } else {
                        _errorToastEmail.value = true
                    }
                }
            }
        }
    }

    fun doneNavigationRegister() {
        _navigateToRegister.value = false
    }

    fun doneNavigationUserDetails() {
        _navigateToUserDetails.value = false
    }

    fun doneToast(){
        _errorToast.value = false
        Log.i("MYTAG", "DONE TOASTING")
    }

    fun donetoastErrorUsername() {
        _errorToastEmail .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastInvalidPassword() {
        _errorToastInvalidPassword .value = false
        Log.i("MYTAG", "Done taoasting ")
    }



    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}