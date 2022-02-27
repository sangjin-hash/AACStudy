package com.example.toyapp.network

import com.example.toyapp.model.Data
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService:ApiService){

    suspend fun getData():List<Data> = apiService.getData()
}