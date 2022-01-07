package com.example.toyapp.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.toyapp.repository.User


//Data Access Object -> 데이터에 접근할 수 있는 method를 정의해놓은 interface
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)            // 중복된 내용이 있을 때 덮어쓰기
    fun insert(user: User)

    @Query("SELECT userId FROM user")
    fun select_id(): List<String>

    @Query("SELECT userPassword FROM user WHERE userId = :inputId")
    fun getPassword(inputId:String): String

}