package com.example.animalcrossing.data.datasource.animal

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("feeding_status")

class FeedingStatusManager(private val context: Context) {

    companion object {
        val MONDAY_KEY = booleanPreferencesKey("fed_monday")
        val TUESDAY_KEY = booleanPreferencesKey("fed_tuesday")
        val WEDNESDAY_KEY = booleanPreferencesKey("fed_wednesday")
        val THURSDAY_KEY = booleanPreferencesKey("fed_thursday")
        val FRIDAY_KEY = booleanPreferencesKey("fed_friday")
        val SATURDAY_KEY = booleanPreferencesKey("fed_saturday")
        val SUNDAY_KEY = booleanPreferencesKey("fed_sunday")
    }


    suspend fun setFedStatus(day: String, fed: Boolean) {
        val key = getKeyForDay(day)
        context.dataStore.edit { preferences ->
            preferences[key] = fed
        }
    }

    fun getFedStatus(day: String): Flow<Boolean> {
        val key = getKeyForDay(day)
        return context.dataStore.data.map { preferences ->
            preferences[key] ?: false
        }
    }

    private fun getKeyForDay(day: String) = when (day.uppercase()) {
        "MONDAY" -> MONDAY_KEY
        "TUESDAY" -> TUESDAY_KEY
        "WEDNESDAY" -> WEDNESDAY_KEY
        "THURSDAY" -> THURSDAY_KEY
        "FRIDAY" -> FRIDAY_KEY
        "SATURDAY" -> SATURDAY_KEY
        "SUNDAY" -> SUNDAY_KEY
        else -> throw IllegalArgumentException("Invalid day")
    }


}

