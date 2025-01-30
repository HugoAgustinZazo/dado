package com.example.miprimerproyecto.juegoDado

import android.os.Parcel
import android.os.Parcelable

class Jugador(val nombre: String,var puntuacion: Int, var numjugador: Int, var puntuacionronda: Int): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(nombre)
        p0.writeInt(puntuacion)
        p0.writeInt(numjugador)
        p0.writeInt(puntuacionronda)

    }

    companion object CREATOR : Parcelable.Creator<Jugador> {
        override fun createFromParcel(parcel: Parcel): Jugador {
            return Jugador(parcel)
        }

        override fun newArray(size: Int): Array<Jugador?> {
            return arrayOfNulls(size)
        }
    }


}