package com.example.toyapp.di

import com.example.toyapp.db.user.UserDao
import com.example.toyapp.repository.UserRepository
import com.example.toyapp.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    // Login & Register 관련 DB
    @Provides
    fun provideUserRepository(userDao: UserDao) : UserRepository {
        return UserRepositoryImpl(userDao)
    }
}