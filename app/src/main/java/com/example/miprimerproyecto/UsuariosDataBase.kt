package com.example.miprimerproyecto

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class],version = 1, exportSchema = false)
abstract class UsuariosDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}