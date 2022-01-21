package com.example.toyapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toyapp.model.User
import com.example.toyapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    val userId: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    val userPassword: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    private var _registerState = MutableLiveData<Int>()
    val registerState: LiveData<Int>
        get() = _registerState

    fun register(){
        val isEmpty = isEmpty()
        if (!isEmpty){
            if(userRepository.checkId(userId.value!!)){
                //Id가 중복될 경우
                _registerState.value = 4
            }else
            {
                _registerState.value = 5
                userRepository.insertUser(
                    User(
                    userId = userId.value!!,
                    userPassword = userPassword.value!!
                    )
                )
            }
        }
    }

    fun isEmpty() : Boolean{
        when{
            userId.value.isNullOrEmpty() && userPassword.value.isNullOrEmpty() -> {
                _registerState.value = 1
                return true
            }

            userId.value.isNullOrEmpty() -> {
                _registerState.value = 2
                return true
            }
            userPassword.value.isNullOrEmpty() -> {
                _registerState.value = 3
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