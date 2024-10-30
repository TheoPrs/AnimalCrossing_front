package com.example.animalcrossing.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AnimalCard(
    val name: String,
    val hasEatenToday: Boolean
)

@Composable
fun AnimalCard(animal: AnimalCard, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEAC9B8))
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Column {
                Text(
                    text = animal.name,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = if (animal.hasEatenToday) "A mangé aujourd'hui" else "N'a pas mangé",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}


@Composable
fun AnimalList(animals: List<AnimalCard>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        for (animal in animals) {
            AnimalCard(animal = animal, onClick = {

            })
        }
    }
}