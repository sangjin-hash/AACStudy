package com.example.toyapp.repository

import android.util.Log
import com.example.toyapp.model.ApiUser
import com.example.toyapp.network.ApiServiceImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl){
    fun getUsers(): Flow<List<ApiUser>> = flow{
        emit(apiServiceImpl.getUsers())
        delay(2000)
        Log.d("[TEST]", "API 호출");
    }
}