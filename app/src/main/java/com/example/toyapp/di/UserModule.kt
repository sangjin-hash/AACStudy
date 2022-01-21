package com.example.toyapp.di

import com.example.toyapp.repository.UserDao
import com.example.toyapp.repository.UserRepository
import com.example.toyapp.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideUserRepository(userDao: UserDao) : UserRepository {
        return UserRepositoryImpl(userDao)
    }
}