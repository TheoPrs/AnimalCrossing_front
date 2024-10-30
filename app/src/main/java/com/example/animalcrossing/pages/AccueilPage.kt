package com.example.animalcrossing.pages

import AnimalFactViewModel
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animalcrossing.components.AnimalCard
import com.example.animalcrossing.components.AnimalList
import com.example.animalcrossing.data.repository.animal.AnimalRepository

@Composable
fun AccueilPage(
    viewModel: AnimalFactViewModel = viewModel(),
    repository: AnimalRepository = viewModel(),
    context: Context
) {
    val animalFact by viewModel.animalFact.collectAsState()
    val animals = repository.animals

    LaunchedEffect(Unit) {
        viewModel.fetchAnimalFact()
        repository.fetchAnimals()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        animalFact?.let { fact ->
            Text(
                text = fact,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(top = 40.dp)
            )
        } ?: run {
            Text(
                text = "Loading...",
                color = Color.White,
                modifier = Modifier.padding(top = 200.dp)
            )
        }

        Text(
            text = "Mes animaux",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(top = 40.dp)
        )

        if (animals.isEmpty()) {
            Text(
                text = "Aucun animal trouvé.",
                color = Color.White,
                modifier = Modifier.padding(top = 20.dp)
            )
        } else {
            AnimalList(animals = animals.map {
                AnimalCard(name = it.name, hasEatenToday = false) //imageRes = it.image)
            })
        }
    }
}

