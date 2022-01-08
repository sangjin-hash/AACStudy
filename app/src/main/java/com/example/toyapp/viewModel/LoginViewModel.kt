package com.example.toyapp.viewModel

import android.app.Application
import android.widget.Toast
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

    private val _loginState = MutableLiveData<Int>()
    val loginState: LiveData<Int>
        get() = _loginState

    val _isRegister = MutableLiveData<Boolean>()
    val isRegister : LiveData<Boolean>
        get() = _isRegister

    private val repository = Repository()

    fun register(){0
        _isRegister.value = true
    }

    fun registerComplete(){
        _isRegister.value = false
    }

    fun login(){
        // TODO : Room DB에 접근하여 유효성 검사(빈칸이 있는지, DB에 data가 없는지) & return true(로그인 성공)/false(로그인 실패)
        val isEmpty = isEmpty()
    }

    fun isEmpty() : Boolean{
        when{
            userId.value.isNullOrEmpty() && userPassword.value.isNullOrEmpty() -> {
                _loginState.value = 1
                return true
            }

            userId.value.isNullOrEmpty() -> {
                _loginState.value = 2
                return true
            }
            userPassword.value.isNullOrEmpty() -> {
                _loginState.value = 3
                return true
            }
        }
        return false
    }

    // Callback called when the ViewModel is destroyed
    override fun onCleared() {
        super.onCleared()
    }
}