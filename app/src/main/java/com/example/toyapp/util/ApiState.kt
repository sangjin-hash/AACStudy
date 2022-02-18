package com.example.toyapp.util

import com.example.toyapp.model.Data

sealed class ApiState {
    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val body: List<Data>) : ApiState()
    object Empty : ApiState()
}