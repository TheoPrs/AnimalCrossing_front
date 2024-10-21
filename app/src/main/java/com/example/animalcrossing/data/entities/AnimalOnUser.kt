package com.example.animalcrossing.data.entities

import kotlinx.serialization.Serializable


@Serializable
data class AnimalOnUser(
    val id_meal: Int,
    val id_animal: Int,
    val id: Int
)