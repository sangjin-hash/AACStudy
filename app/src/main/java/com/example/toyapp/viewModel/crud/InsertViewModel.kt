package com.example.toyapp.viewModel.crud

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyapp.repository.NetworkRepository
import com.example.toyapp.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertViewModel @Inject constructor(private var networkRepository: NetworkRepository) : ViewModel() {
    private val dataStateFlow: MutableStateFlow<ApiState>
            = MutableStateFlow(ApiState.Empty)

    val userName : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val userHobby : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val userPhone : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    val _dataStateFlow : StateFlow<ApiState> = dataStateFlow


    fun insert_person(){
        var name_person : String = userName.value.toString()
        var hobby_person : String = userHobby.value.toString()
        var phone_person : String = userPhone.value.toString()

        insertData(name_person, hobby_person, phone_person)
    }

    fun insertData(name: String, hobby: String, phone: String) = viewModelScope.launch {
        networkRepository.insertData(name, hobby, phone)
            .catch { e->
                dataStateFlow.value = ApiState.Failure(e)
            }.collect{ data ->
                dataStateFlow.value = ApiState.Success_insert(data)
            }
    }
}