package com.example.miprimerproyecto

import android.os.Parcel
import android.os.Parcelable

class Jugador(val nombre: String,var puntuacion: Int, var numjugador: Int, var puntuacionronda: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(puntuacion)
        parcel.writeInt(numjugador)
        parcel.writeInt(puntuacionronda)
    }

    override fun describeContents(): Int {
        return 0
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