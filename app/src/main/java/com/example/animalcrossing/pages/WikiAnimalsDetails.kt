package com.example.animalcrossing.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.animalcrossing.ui.theme.Pink80
import com.example.animalcrossing.ui.theme.Purple80
import com.example.animalcrossing.viewmodels.AnimalWikiDetailsViewModel
import com.example.animalcrossing.viewmodels.factory.AnimalWikiDetailsViewModelFactory
import java.time.format.TextStyle


@Composable
fun WikiAnimalsDetails(animalId: Int) {
    val factory = AnimalWikiDetailsViewModelFactory(animalId)
    val animalDetails: AnimalWikiDetailsViewModel = viewModel(factory = factory)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
            .padding(16.dp)
    ) {
        animalDetails.animals?.let { animal ->
            AnimalImages2(imagePath = animal.image_path)

            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEAC9B8), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = animal.name,
                    style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                )
                Text(
                    text = animal.specy,
                    style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp, color = Color.Black)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = animal.paragraphe_animal,
                style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp, color = Color(0xFFEAC9B8)),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF333333), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEAC9B8), RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Hauteur: ${animal.height}",
                    style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp, color = Color.Black)
                )
                Text(
                    text = "Poids: ${animal.weight}",
                    style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp, color = Color.Black)
                )
            }
        }
    }
}


@Composable
fun AnimalImages2(imagePath: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
//            .padding(top = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = imagePath,
            contentDescription = "Image d'un animal",
            modifier = Modifier
                .size(412.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
//    WikiAnimalsDetails()
}