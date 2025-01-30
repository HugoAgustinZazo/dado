package com.example.miprimerproyecto.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [User::class],version = 1, exportSchema = false)
abstract class UsuariosDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}