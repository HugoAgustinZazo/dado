package com.example.miprimerproyecto

import android.app.Application
import androidx.room.Room

class Aplicacion: Application() {

    companion object{
        lateinit var usuariosDataBase: UsuariosDataBase
    }

    override fun onCreate() {
        super.onCreate()
        usuariosDataBase= Room.databaseBuilder(applicationContext,UsuariosDataBase::class.java,"bd_haz").build()
    }
}