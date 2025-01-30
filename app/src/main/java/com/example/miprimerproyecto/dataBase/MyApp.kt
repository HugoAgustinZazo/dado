package com.example.miprimerproyecto.dataBase

import android.app.Application
import androidx.room.Room

class MyApp : Application() {

    companion object {
        lateinit var appDatabase: UsuariosDataBase
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(applicationContext, UsuariosDataBase::class.java, "bd_haz").build()
    }
}