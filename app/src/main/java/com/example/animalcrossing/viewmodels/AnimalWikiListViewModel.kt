package com.example.animalcrossing.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalcrossing.data.entities.AnimalWiki
import com.example.animalcrossing.supabase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalWikiListViewModel: ViewModel() {
    private val _animals = mutableStateListOf<AnimalWiki>()
    val animals: List<AnimalWiki> get() = _animals

    init {
        fetchAnimals()
    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                supabase.from("animal_wiki").select().decodeList<AnimalWiki>()
            }
            _animals.addAll(results)
        }
    }
}