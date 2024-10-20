package com.example.animalcrossing.data.entities


import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.sql.Date

@Serializable
data class VaccinationCard (
    val id_card: Int,
    val vaccine: String,
    @Contextual val endOfValidty : Date
)