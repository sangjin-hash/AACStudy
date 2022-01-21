package com.example.toyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "userId")
    val userId: String,

    @ColumnInfo(name = "userPassword")
    val userPassword: String
    )