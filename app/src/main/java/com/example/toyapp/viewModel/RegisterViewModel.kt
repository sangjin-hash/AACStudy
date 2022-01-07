package com.example.toyapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {
    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get() = _userId

    private val _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String>
        get() = _userPassword
}