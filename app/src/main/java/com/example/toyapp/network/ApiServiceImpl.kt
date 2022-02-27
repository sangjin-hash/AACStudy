package com.example.toyapp.network

import com.example.toyapp.model.ApiUser
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers(): List<ApiUser> = apiService.getData()
}