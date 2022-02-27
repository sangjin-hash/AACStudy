package com.example.toyapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.toyapp.db.crud.CrudDao
import com.example.toyapp.db.user.UserDao
import com.example.toyapp.model.Data
import com.example.toyapp.model.User

// crud라는 테이블을 추가하면서 entity에 data class를 하나 더 추가해야 하고, 변경사항이 있기 때문에 버전을 변경해야 한다.
@Database(
    version = 2,
    entities = [User::class, Data::class],
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun crudDao(): CrudDao
}

