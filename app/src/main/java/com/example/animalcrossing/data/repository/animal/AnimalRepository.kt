package com.example.animalcrossing.data.repository.animal

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalcrossing.data.entities.Animal
import com.example.animalcrossing.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.result.PostgrestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalRepository : ViewModel() {
    private val _animals = mutableStateListOf<Animal>()
    val animals: List<Animal> get() = _animals

    fun fetchAnimals() {
        viewModelScope.launch {
            try {
                val results: PostgrestResult = withContext(Dispatchers.IO) {
                    supabase.from("animal").select()
                }

                val animalsList = results.decodeList<Animal>()
                Log.i("AnimalRepository", "Animaux récupérés : $animalsList")
                _animals.clear()
                _animals.addAll(animalsList)
            } catch (e: Exception) {
                Log.e("AnimalRepository", "Erreur lors de la récupération des animaux : ${e.message}")
            }
        }
    }
}

