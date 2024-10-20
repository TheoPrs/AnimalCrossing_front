package com.example.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String,
    // Ajoute d'autres champs selon la réponse de Supabase
)