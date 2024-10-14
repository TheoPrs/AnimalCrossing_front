package com.example.animalcrossing.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Animal(
    val name: String,
    val hasEatenToday: Boolean,
    val imageRes: Int
)

@Composable
fun AnimalCard(animal: Animal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            val image: Painter = painterResource(id = animal.imageRes)
            Image(
                painter = image,
                contentDescription = "Animal image",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = animal.name,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = if (animal.hasEatenToday) "A mangé aujourd'hui" else "N'a pas mangé",
                    fontSize = 14.sp,
                    color = if (animal.hasEatenToday) Color.Green else Color.Red,
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}

@Composable
fun AnimalList(animals: List<Animal>) {
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