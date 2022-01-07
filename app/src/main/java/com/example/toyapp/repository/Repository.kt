package com.example.toyapp.repository
/*
 Repository class 설명
 - User(Model) + UserDatabase(DB 생성 abstract class) + UserDao(DB 접근하는 query관련 method interface) => Room
 - Repository class -> DB를 활용하는 class
    1) db 생성
    2) db.userDao() -> 생성한 db 내에 UserDao의 interface 접근 가능
    3) 1과 2를 가지고 DB를 활용하여 ViewModel에서 활용할 수 있는 method를 정의한다.
 */

class Repository(){

    private val db = UserDatabase.getInstance()                  //db 생성

    fun insertUser(user : User){
        db!!.userDao().insert(user)
    }

    fun checkPassword(userId: String, userPassword: String): Boolean{       // password validation method
        val password:String = db!!.userDao().getPassword(userId)
        return password == userPassword
    }

    fun checkId(userId: String): Boolean{                                   //ID 중복여부 확인 method
        val userList = db!!.userDao().select_id()
        userList.forEach{   id ->
            if(id == userId) return true
        }
        return false
    }
}