package com.example.toyapp.repository

import com.example.toyapp.db.user.UserDao
import com.example.toyapp.model.User

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun insertUser(user: User) {
        userDao.insert(user)
    }

    override fun checkPassword(userId: String, userPassword: String): Boolean {
        val password:String = userDao.getPassword(userId)
        return password == userPassword
    }

    override fun checkId(userId: String): Boolean {
        val userList = userDao.select_id()
        userList.forEach{ id ->
            if(id == userId) return true
        }
        return false
    }
}
