package com.example.toyapp.db.crud

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.toyapp.model.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface CrudDao {

    @Query("SELECT * FROM crud")
    fun getAllCrud(): Flow<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrud(crud : List<Data>)

    @Query("DELETE FROM crud")
    suspend fun deleteAllCrud()
}