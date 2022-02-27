package com.example.toyapp.util

import com.example.toyapp.model.Data

sealed class ApiState {
    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    // Select 했을 때 성공
    class Success(val body: List<Data>) : ApiState()

    object Empty : ApiState()
}