package com.example.animalcrossing.pages

import com.example.animalcrossing.viewmodel.AnimalFactViewModel
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
import com.example.animalcrossing.R.drawable
import com.example.animalcrossing.components.Animal
import com.example.animalcrossing.components.AnimalList


@Composable
fun AccueilPage(viewModel: AnimalFactViewModel = viewModel()) {

    val animalFact by viewModel.animalFact.collectAsState()

    val animals = listOf(
        Animal(name = "FouFou", hasEaten = true,poids = 9, age = "12 ans", sexe = "female", espece = "blabla", imageRes = drawable.image_foufou),
        Animal(name = "Rex", hasEaten = false,poids = 4, age = "2 ans", sexe = "male", espece = "blabla", imageRes = drawable.image_rex),
        Animal(name = "Bella", hasEaten = true, poids = 4, age = "2 ans", sexe = "male", espece = "blabla",imageRes = drawable.image_bella)
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

        // Barre de recherche
        SearchBar(
            hint = "Search for an animal"
        )


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

@Composable
fun SearchBar(hint: String) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .height(50.dp)
            .background(Color(0xFFEAC9B8), CircleShape),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF755F4E),
                modifier = Modifier.size(20.dp)
            )

            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color(0xFF755F4E),
                modifier = Modifier.size(24.dp)
            )
        }

        if (text.isEmpty()) {
            Text(
                text = hint,
                style = TextStyle(color = Color(0xFF755F4E), fontSize = 16.sp),
                modifier = Modifier.padding(start = 40.dp)
            )
        }
    }
}
