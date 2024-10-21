package com.example.animalcrossing.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class AnimalMeal(
    val id_meal: Int,
    val quantity: Int,
    val recipee: String
)