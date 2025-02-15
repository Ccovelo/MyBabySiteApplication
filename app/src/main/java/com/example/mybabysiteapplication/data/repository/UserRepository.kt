package com.example.mybabysiteapplication.data.repository
import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {
    companion object {
        private val USERNAME_KEY = stringPreferencesKey("username")
    }

    val username: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[USERNAME_KEY]
    }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = username
        }
    }
}
class UserRepository @Inject constructor(
    private val preferences: UserPreferences
) {
    suspend fun saveUsername(username: String) {
        preferences.saveUsername(username)
    }

    fun getUsername(): Flow<String?> {
        return preferences.username
    }
}