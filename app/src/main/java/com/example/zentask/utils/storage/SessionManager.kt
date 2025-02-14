import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

val Context.dataStore by preferencesDataStore(name = "zentask_app")

class StorageManager(private val context: Context) {
    suspend fun save(name: String,  token: String) {
        val tokenKey = stringPreferencesKey(name)
        context.dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }

    fun getItem(name: String): String? {
        val tokenKey = stringPreferencesKey(name)
        return runBlocking {
            context.dataStore.data.first()[tokenKey]
        }
    }

    suspend fun clear(name: String) {
        val itemName = stringPreferencesKey(name)
        context.dataStore.edit { preferences ->
            preferences.remove(itemName)
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { preferences -> preferences.clear() }
    }
}
