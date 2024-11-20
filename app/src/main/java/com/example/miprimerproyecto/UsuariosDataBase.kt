package com.example.miprimerproyecto

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Usuario::class], version = 1)
abstract class UsuariosDataBase: RoomDatabase() {
    abstract fun UsuarioDao(): UsuarioDao
}