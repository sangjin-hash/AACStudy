package com.example.toyapp.di

import android.content.Context
import androidx.room.Room
import com.example.toyapp.repository.UserDao
import com.example.toyapp.repository.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideUserDatabase(@ApplicationContext context : Context) : UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_db").allowMainThreadQueries().build()
    }

    @Provides
    fun provideUserDao(userDatabase: UserDatabase) : UserDao {
        return userDatabase.userDao()
    }
}