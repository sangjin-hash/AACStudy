package com.example.toyapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toyapp.repository.Repository

class LoginViewModel() : ViewModel() {

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get() = _userId

    private val _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String>
        get() = _userPassword

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean>
        get() = _loginState

    val _isRegister = MutableLiveData<Boolean>()
    val isRegister : LiveData<Boolean>
        get() = _isRegister

    private val repository = Repository()

    fun register(){
        _isRegister.value = true
    }

    fun registerComplete(){
        _isRegister.value = false
    }

    fun login(){
        // TODO : Room DB에 접근하여 유효성 검사(빈칸이 있는지, DB에 data가 없는지) & return true(로그인 성공)/false(로그인 실패)
    }

    // Callback called when the ViewModel is destroyed
    override fun onCleared() {
        super.onCleared()
    }
}