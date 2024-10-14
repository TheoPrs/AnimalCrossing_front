package com.example.animalcrossing.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.animalcrossing.components.AddAnimal
import com.example.animalcrossing.components.Animal
import com.example.animalcrossing.components.AnimalViewModel


@Composable
fun ProfilePage(navController: NavHostController, viewModel: AnimalViewModel = viewModel()) {
    val animals = viewModel.animals
    val showForm = viewModel.showForm

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(animals.size) { index ->
                    AnimalItem(animal = animals[index], navController = navController)
                }
            }
        }


        FloatingActionButton(
            onClick = { viewModel.openForm() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFFEAC9B8)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Animal",
                tint = Color.White
            )
        }
        if (showForm.value) {
            AddAnimal(
                onAddAnimal = { name, age, poids, sexe, espece, imageRes ->
                    viewModel.addAnimal(name, age, poids, sexe, espece, imageRes)
                },
                onDismiss = { viewModel.closeForm() }
            )
        }
    }
}

@Composable
fun AnimalItem(animal: Animal, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFEAC9B8), CircleShape)
            .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
            .clickable {

            }
    ) {
        if (animal.imageRes != null) {
            Image(
                painter = painterResource(id = animal.imageRes),
                contentDescription = "Animal Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(90.dp)
                    .padding(12.dp),
                contentScale = ContentScale.Crop
            )
        } else if (animal.imageUrl != null) {
            AsyncImage(
                model = animal.imageUrl,
                contentDescription = "Animal Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(90.dp)
                    .padding(12.dp),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = animal.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "${animal.age} ans • ${animal.poids} kg",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
