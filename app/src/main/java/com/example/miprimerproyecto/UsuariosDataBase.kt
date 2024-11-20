package com.example.miprimerproyecto

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Usuario::class], version = 1)
abstract class UsuariosDataBase: RoomDatabase() {
    abstract fun UsuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: UsuariosDataBase? = null

        fun getDatabase(context: Context): UsuariosDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuariosDataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}