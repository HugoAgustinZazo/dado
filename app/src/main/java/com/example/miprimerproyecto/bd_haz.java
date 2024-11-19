package com.example.miprimerproyecto;

import androidx.room.Database;

@Database(entities = [Usuario::class], version = 1)
abstract class bd_haz : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}
