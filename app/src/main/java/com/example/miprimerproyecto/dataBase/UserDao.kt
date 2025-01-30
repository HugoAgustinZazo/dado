package com.example.miprimerproyecto.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM usuarios")
    fun getAll(): List<User>

    @Query("SELECT * FROM usuarios WHERE username LIKE :username")
    fun findByName(username: String): User

    @Insert
    fun insertUser(user: User)

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}