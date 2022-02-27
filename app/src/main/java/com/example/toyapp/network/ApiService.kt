package com.example.toyapp.network

import com.example.toyapp.model.ApiUser
import retrofit2.http.GET

interface ApiService {
    @GET("select.php")
    suspend fun getData():List<ApiUser>
}