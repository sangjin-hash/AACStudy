package com.example.toyapp.di

import android.content.Context
import androidx.room.Room
import com.example.toyapp.db.UserDatabase
import com.example.toyapp.db.user.UserDao
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
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()      // Room Migration(Table 한개 추가했음) + 이거만 추가하면 기존의 db 다날라감
            .build()
    }

    @Provides
    fun provideUserDao(userDatabase: UserDatabase) : UserDao {
        return userDatabase.userDao()
    }


}