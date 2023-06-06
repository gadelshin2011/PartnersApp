package com.example.partnersapp.presentation.db


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preference")

class DataStoreManager(context: Context) {


    private val dataStore = context.dataStore

    suspend fun saveToken(token: String) {
        val prefsKey = stringPreferencesKey("my_key")
        dataStore.edit { preferences ->
            preferences[prefsKey] = token
        }
    }

    suspend fun loadToken(): String? {
        val prefsKey = stringPreferencesKey("my_key")
        val prefs = dataStore.data.first()
        return prefs[prefsKey]
    }
}