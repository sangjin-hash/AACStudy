package com.example.toyapp.repository

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.toyapp.App
import com.example.toyapp.repository.dao.UserDao

//DB를 생성하는 abstract class => 추후에 Hilt를 통해 다음 클래스를 외부에서 주입하여 활용하는 방법을 활용할 예정.
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    // Singleton
    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(): UserDatabase?{                   // 1. getInstance(context:Context)로 함수를 만들고,
            if(instance == null){
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(
                        App.context(),                      // 2. 여기서 context.applicationContext를 주로 활용하지만, 다른 클래스에서 getInstance를 호출할 때 context때문에 매우 불편하므로 Singleton으로 App.kt를 생성하였고, 다음과 같이 수정
                        UserDatabase::class.java,
                        "user_db"
                    )
                        .allowMainThreadQueries()           // 추후에 Coroutine을 이용할 예정 : 현재는 비동기 프로그래밍을 사용하지 않으므로 MainThread에서 처리하도록 함.
                        .build()
                }
            }
            return instance
        }
    }
}