package com.example.toyapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crud")
data class Data(
    @PrimaryKey val id : Int,
    val name : String,
    val hobby : String,
    val phone : String
)
