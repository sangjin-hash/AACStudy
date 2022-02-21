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
class DeleteViewModel @Inject constructor(private var networkRepository: NetworkRepository) : ViewModel() {
    private val dataStateFlow: MutableStateFlow<ApiState>
        = MutableStateFlow(ApiState.Empty)

    val _dataStateFlow : StateFlow<ApiState> = dataStateFlow

    val userId : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun delete_person(){
        var id : Int = Integer.parseInt(userId.value)
        deleteData(id)
    }

    fun deleteData(id: Int) = viewModelScope.launch {
        networkRepository.deleteData(id)
            .catch { e ->
                dataStateFlow.value = ApiState.Failure(e)
            }.collect { data ->
                dataStateFlow.value = ApiState.Success_String(data)
            }
    }
}