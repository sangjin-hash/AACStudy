package com.example.toyapp.repository

import com.example.toyapp.model.Data
import com.example.toyapp.network.ApiServiceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl){
    fun getPost(): Flow<List<Data>> = flow{

    }
}