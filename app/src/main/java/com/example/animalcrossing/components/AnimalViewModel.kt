package com.example.animalcrossing.components

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.animalcrossing.R

data class Animal(
    val name: String,
    val age: String,
    val poids: Int,
    val sexe: String,
    val espece: String,
    val imageRes: Int,
    val hasEaten: Boolean
) {
    val imageUrl: String
        get() {
            return "http://example.com/images/${name}.png" // Remplace par ta logique
        }
}

class AnimalViewModel : ViewModel() {
    val animals = mutableStateListOf(
        Animal("Doudou", "11", 4, "Mâle", "Chien", R.drawable.chien2, false),
        Animal("Foufou", "3", 14, "Femelle", "Chien", R.drawable.chien2, false)
    )

    val showForm = mutableStateOf(false)

    fun addAnimal(name: String, age: Int, poids: Int, sexe: String, espece: String, imageRes: Int) {
        animals.add(Animal(name, age.toString(), poids, sexe, espece, imageRes, false))
        showForm.value = false
    }

    fun closeForm() {
        showForm.value = false
    }

    fun openForm() {
        showForm.value = true
    }
}