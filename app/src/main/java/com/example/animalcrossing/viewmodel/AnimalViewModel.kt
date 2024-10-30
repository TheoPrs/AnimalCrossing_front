package com.example.animalcrossing.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalcrossing.data.entities.Animal
import com.example.animalcrossing.data.repository.animal.AnimalRepository

class AnimalViewModel : ViewModel() {

    private val repository = AnimalRepository()

    val animals: List<Animal> get() = repository.animals

    init {
        repository.fetchAnimals()
    }
}
