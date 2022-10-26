package com.example.loginappmvvm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RegisterDao {

    @Insert
    fun insert(register: RegisterEntity)

    @Query("SELECT * FROM register_users ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("SELECT * FROM register_users WHERE email LIKE :email")
    fun getEmailUsers(email: String): RegisterEntity?
}