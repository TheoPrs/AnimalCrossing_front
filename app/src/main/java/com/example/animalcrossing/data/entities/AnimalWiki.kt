package com.example.animalcrossing.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class AnimalWiki(
    val id_animal: Int,
    val name: String,
    val specy: String? = null,
    val alimentation: String? = null,
    val height: String? = null,
    val weight: String? = null,
    val temperament: String? = null,
    val environment: String? = null,
    val paragraphe_animal: String? = null
)
