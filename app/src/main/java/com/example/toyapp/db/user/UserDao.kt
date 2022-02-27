package com.example.toyapp.db.user


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.toyapp.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT userId FROM user")
    fun select_id(): List<String>

    @Query("SELECT userPassword FROM user WHERE userId = :inputId")
    fun getPassword(inputId:String): String

}