package com.example.animalcrossing.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.animalcrossing.R
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter


data class Animal(
    val name: String,
    val age: String,
    val weight: String,
    val imageRes: Int? = null,  // Image locale
    val imageUrl: String? = null // Image distante
)




@Composable
fun ProfilePage(navController: NavHostController) {

    val animals = remember {
        mutableStateListOf(
            Animal("Doudou", "11 mois", "4 kg", R.drawable.chien2), // Utilisez directement R.drawable.chien2
            Animal("Foufou", "3 ans", "14 kg", R.drawable.chien2) // Utilisez directement R.drawable.chien2
        )
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
            .padding(16.dp)
    ) {


        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(animals.size) { index ->
                AnimalItem(animal = animals[index], navController = navController)
            }
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
                text = "${animal.age} • ${animal.weight}",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}


