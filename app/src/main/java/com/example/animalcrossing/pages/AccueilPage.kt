package com.example.animalcrossing.pages

import AnimalFactViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.animalcrossing.R
import com.example.animalcrossing.components.AnimalCard
import com.example.animalcrossing.components.AnimalList


@Composable
fun AccueilPage(viewModel: AnimalFactViewModel = viewModel()) {

    val animalFact by viewModel.animalFact.collectAsState()

    val animals = listOf(
        AnimalCard(name = "FouFou", hasEatenToday = true, imageRes = R.drawable.image_foufou),
        AnimalCard(name = "Rex", hasEatenToday = false, imageRes = R.drawable.image_rex),
        AnimalCard(name = "Bella", hasEatenToday = true, imageRes = R.drawable.image_bella)
    )

    LaunchedEffect(Unit) {
        viewModel.fetchAnimalFact()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

//        // Barre de recherche
//        SearchBar(
//            hint = "Search for an animal"
//        )


        animalFact?.let { fact ->
            Text(
                text = fact,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 40.dp)
            )
        } ?: run {
            Text(
                text = "Loading...",
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp)
            )
        }

        AnimalList(animals = animals)
    }
}


