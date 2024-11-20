package com.example.miprimerproyecto

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuarios")
    fun getAll(): List<Usuario>

    @Query("SELECT * FROM usuarios WHERE username IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Usuario>

    @Query("SELECT * FROM usuarios WHERE username LIKE :username")
    fun findByName(username: String): Usuario

    @Insert
    fun insertAll(vararg users: Usuario)

    @Insert
    fun insert(user: Usuario)

    @Delete
    fun delete(user: Usuario)
}