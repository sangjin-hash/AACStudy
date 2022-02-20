package com.example.toyapp.util

import com.example.toyapp.model.Data
import com.example.toyapp.model.Message

sealed class ApiState {
    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    // Select 했을 때 성공
    class Success(val body: List<Data>) : ApiState()

    // Insert 했을 때 성공
    class Success_insert(val body: Message) : ApiState()
    object Empty : ApiState()
}