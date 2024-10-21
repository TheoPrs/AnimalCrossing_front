package com.example.animalcrossing.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class AnimalWiki(
    val id_animal: Int,
    val name: String,
    val specy: String,
    val alimentation: String,
    val height: String,
    val weight: String,
    val temperament: String,
    val environment: String,
    val paragraphe_animal: String,
    val image_path: String
)
