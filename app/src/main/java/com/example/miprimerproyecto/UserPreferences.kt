package com.example.miprimerproyecto

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")
class UserPreferences(private val context: Context) {

    companion object {
        val NAME_KEY = stringPreferencesKey("user_name")
        val PASSWORD_KEY = stringPreferencesKey("user_password")
        val ACTIVADO = booleanPreferencesKey("ACTIVADO")
    }

    suspend fun saveUserData(name: String, password: String, activado: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
            preferences[PASSWORD_KEY] = password
            preferences[ACTIVADO] = activado

        }
    }
    suspend fun limpiraDatos(){
        context.dataStore.edit { preferences ->
            preferences.clear()

        }
    }
    val userData: Flow<UserDataCredenciales> = context.dataStore.data.map { preferences ->
        UserDataCredenciales(
            username = preferences[NAME_KEY] ?: "",
            password = preferences[PASSWORD_KEY] ?: "",
            isChecked = preferences[ACTIVADO] ?: false
        )
    }
}