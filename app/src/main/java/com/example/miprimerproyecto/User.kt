package com.example.miprimerproyecto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "usuarios")
data class User(
    @PrimaryKey val username: String,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "fechanac") val fechanac: String?
)
