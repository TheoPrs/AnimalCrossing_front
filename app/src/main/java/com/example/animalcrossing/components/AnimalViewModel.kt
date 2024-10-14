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
    val imageRes: Int
) {
    val imageUrl: Any
        get() {
            TODO()
        }
}

class AnimalViewModel : ViewModel() {

    val animals = mutableStateListOf(
        Animal("Doudou", 11.toString(), 4, "Mâle", "Chien", R.drawable.chien2),
        Animal("Foufou", 3.toString(), 14, "Femelle", "Chien", R.drawable.chien2)
    )


    val showForm = mutableStateOf(false)


    fun addAnimal(name: String, age: Int, poids: Int, sexe: String, espece: String, imageRes: Int) {
        animals.add(Animal(name, age.toString(), poids, sexe, espece, imageRes))
        showForm.value = false
    }



    fun closeForm() {
        showForm.value = false
    }


    fun openForm() {
        showForm.value = true
    }
}
