package com.example.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val access_token: String,
    val refresh_token: String,
    val user: User
)