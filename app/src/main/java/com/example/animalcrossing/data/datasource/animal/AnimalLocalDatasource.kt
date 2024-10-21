package com.example.animalcrossing.data.datasource.animal

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AnimalLocalDatasource {
}

val Context.dataStore by preferencesDataStore(name = "feeding_status")

class FeedingStatusManager(private val context: Context) {

    private val MONDAY_FEEDING_STATUS = booleanPreferencesKey("monday_feeding_status")

    // Fetch saved status
    val mondayFeedingStatus: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[MONDAY_FEEDING_STATUS] ?: false
        }

    // Save the feeding status for Monday
    suspend fun saveMondayFeedingStatus(isFed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[MONDAY_FEEDING_STATUS] = isFed
        }
    }
}