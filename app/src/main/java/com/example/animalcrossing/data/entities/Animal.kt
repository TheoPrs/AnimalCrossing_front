package com.example.animalcrossing.data.entities

import kotlinx.serialization.Serializable


@Serializable
data class Animal (
    val id_animal: Int,
    val name: String,
    val specy: String,
    val age: Int,
    val weight:  Int,
    val height: Int,
    val vaccinationCard: VaccinationCard,
    val meals: AnimalMeal
)