package com.example.toyapp.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toyapp.repository.Repository

class LoginViewModel() : ViewModel() {
    companion object{
        private const val TAG = "[LoginViewModel]"
    }

    val userId: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    val userPassword: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    private var _loginState = MutableLiveData<Int>()
    val loginState: LiveData<Int>
        get() = _loginState

    private val _isRegister = MutableLiveData<Boolean>()
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
        val isEmpty = isEmpty()
        if(!isEmpty){
            if(repository.checkPassword(userId.value!!, userPassword.value!!)){
                _loginState.value = 5
            }else{
                _loginState.value = 4
            }
        }
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