package com.example.toyapp.network

import com.example.toyapp.model.Data
import com.example.toyapp.model.Message
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("select.php")
    suspend fun getData():List<Data>

    @GET("delete.php")
    suspend fun deleteData(@Query("id")id : Int):String

    @GET("update.php")
    suspend fun updateData(
        @Query("id")id : Int,
        @Query("name")name : String,
        @Query("hobby")hobby : String,
        @Query("phone")phone : String
    ) : String

    @POST("insert.php")
    suspend fun insertData(
        @Query("name")name: String,
        @Query("hobby")hobby : String,
        @Query("phone")phone : String
    ) : Message
}