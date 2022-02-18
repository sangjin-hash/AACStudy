package com.example.toyapp.network

import com.example.toyapp.model.Data
import com.example.toyapp.model.Message
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("select.php")
    suspend fun getData():List<Data>

    @GET("delete.php")
    suspend fun deleteData(id : Int):String

    @GET("update.php")
    suspend fun updateData(data: Data):List<String>

    @POST("insert.php")
    suspend fun insertData(name: String, hobby : String, phone : String):List<Message>
}