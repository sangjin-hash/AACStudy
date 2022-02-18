package com.example.toyapp.di

import com.example.toyapp.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    //base URL
    @Provides
    fun providesUrl() = "http://210.107.198.221/"

    @Provides
    @Singleton
    fun providesApiService(url:String) : ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}