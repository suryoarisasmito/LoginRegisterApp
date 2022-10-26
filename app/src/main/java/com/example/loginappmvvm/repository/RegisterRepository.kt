package com.example.loginappmvvm.repository

import com.example.loginappmvvm.database.RegisterDao
import com.example.loginappmvvm.database.RegisterEntity

class RegisterRepository(private val dao: RegisterDao) {
    val users = dao.getAllUsers()

     fun insert(user: RegisterEntity){
        return dao.insert(user)
    }

     fun getEmail(email: String): RegisterEntity? {
        return dao.getEmailUsers(email)
    }
}