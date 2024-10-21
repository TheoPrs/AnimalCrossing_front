package com.example.app.data.model

import com.example.animalcrossing.data.entities.User
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val access_token: String,
    val refresh_token: String,
    val user: User
)