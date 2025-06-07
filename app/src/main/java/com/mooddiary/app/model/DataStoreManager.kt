package com.mooddiary.app.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")
private const val USER_EMAIL = "email"
private const val USER_PASSWORD = "password"
private const val IS_USER_AUTHORIZE = "is_authorize"
private const val IS_ACCOUNT_CREATED = "is_created"

class DataStoreManager(val context: Context) {

    suspend fun saveUserData(userData: UserData) {
        context.dataStore.edit { data ->
            data[stringPreferencesKey(USER_EMAIL)] = userData.email
            data[stringPreferencesKey(USER_PASSWORD)] = userData.password
            data[booleanPreferencesKey(IS_USER_AUTHORIZE)] = userData.isAuthorize
            data[booleanPreferencesKey(IS_ACCOUNT_CREATED)] = userData.isAccountCreated
        }
    }

    fun getUserData() = context.dataStore.data.map { data ->
        return@map UserData(
            email = data[stringPreferencesKey(USER_EMAIL)] ?: "null",
            password = data[stringPreferencesKey(USER_PASSWORD)] ?: "null",
            isAuthorize = data[booleanPreferencesKey(IS_USER_AUTHORIZE)] ?: false,
            isAccountCreated = data[booleanPreferencesKey(IS_ACCOUNT_CREATED)] ?: false
        )
    }

    suspend fun clearUserData() {
        context.dataStore.edit { data ->
            data.remove(stringPreferencesKey(USER_EMAIL))
            data.remove(stringPreferencesKey(USER_PASSWORD))
            data.remove(booleanPreferencesKey(IS_USER_AUTHORIZE))
            data.remove(booleanPreferencesKey(IS_ACCOUNT_CREATED))
        }
    }
}
