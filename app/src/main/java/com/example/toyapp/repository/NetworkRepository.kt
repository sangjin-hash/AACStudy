package com.example.toyapp.repository

import com.example.toyapp.model.Data
import com.example.toyapp.model.Message
import com.example.toyapp.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl){
    fun getData(): Flow<List<Data>> = flow{
        emit(apiServiceImpl.getData())
    }.flowOn(Dispatchers.IO)

    fun deleteData(id: Int) : Flow<String> = flow{
        emit(apiServiceImpl.deleteData(id))
    }.flowOn(Dispatchers.IO)

    fun updateData(data: Data) : Flow<List<String>> = flow {
        emit(apiServiceImpl.updateData(data))
    }.flowOn(Dispatchers.IO)

    fun insertData(name:String, hobby:String, phone: String) : Flow<List<Message>> = flow{
        emit(apiServiceImpl.insertData(name, hobby, phone))
    }.flowOn(Dispatchers.IO)
}