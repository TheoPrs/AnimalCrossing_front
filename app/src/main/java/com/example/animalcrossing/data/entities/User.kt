package com.example.animalcrossing.data.entities

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id_user: Int,
    val name : String,
    val username: String,
    val mail: String,
    val password: String
)