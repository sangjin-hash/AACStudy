package com.example.toyapp.network

import com.example.toyapp.model.Data
import com.example.toyapp.model.Message
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService:ApiService){

    suspend fun getData():List<Data> = apiService.getData()
    suspend fun deleteData(id : Int):String = apiService.deleteData(id)
    suspend fun updateData(data: Data):List<String> = apiService.updateData(data)
    suspend fun insertData(name: String, hobby : String, phone : String):Message = apiService.insertData(name, hobby, phone)
}