package com.example.toyapp.viewModel.crud

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
class HomeViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {
    private val dataStateFlow: MutableStateFlow<ApiState>
        = MutableStateFlow(ApiState.Empty)

    val _dataStateFlow : StateFlow<ApiState> = dataStateFlow

    fun getData() = viewModelScope.launch {
        dataStateFlow.value = ApiState.Loading
        networkRepository.getData()
            .catch{ e ->
                dataStateFlow.value = ApiState.Failure(e)
            }.collect{ data ->
                dataStateFlow.value = ApiState.Success(data)
            }
    }
}