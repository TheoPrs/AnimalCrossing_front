package com.example.animalcrossing.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalcrossing.data.entities.AnimalWiki
import com.example.animalcrossing.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalWikiDetailsViewModel(private val animalId: Int): ViewModel() {
    private val _animals = mutableStateOf<AnimalWiki?>(null)
    val animals: AnimalWiki? get() = _animals.value

    init {
        fetchAnimals(animalId) // Appelle fetchAnimals avec animalId
    }

    private fun fetchAnimals(animalId: Int) {
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                supabase.from("animal_wiki").select {
                    filter {
                        filter(column = "id_animal", operator = FilterOperator.EQ, value = animalId)
                    }
                }
            }
            _animals.value = results.decodeSingleOrNull()
        }
    }
}