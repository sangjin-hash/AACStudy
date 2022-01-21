package com.example.toyapp.repository

import com.example.toyapp.model.User

interface UserRepository {
    fun insertUser(user: User)
    fun checkPassword(userId: String, userPassword: String) : Boolean
    fun checkId(userId: String) : Boolean
}