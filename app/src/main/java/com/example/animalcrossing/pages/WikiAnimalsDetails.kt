package com.example.animalcrossing.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.animalcrossing.R
import com.example.animalcrossing.data.entities.Animal
import com.example.animalcrossing.data.entities.AnimalWiki
import com.example.animalcrossing.ui.theme.Pink80
import com.example.animalcrossing.ui.theme.Purple80
import com.example.animalcrossing.viewmodels.AnimalWikiDetailsViewModel
import com.example.animalcrossing.viewmodels.factory.AnimalWikiDetailsViewModelFactory


@Composable
fun WikiAnimalsDetails(animalId: Int){
    val factory = AnimalWikiDetailsViewModelFactory(animalId)
    val animalDetails: AnimalWikiDetailsViewModel = viewModel(factory = factory)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Pink80)){
        animalDetails.animals?.let {
            Column {
                AnimalImages2(imagePath = it.image_path)

                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = it.name)
                    Text(text = it.specy)
                }
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Pink80)
                        .padding(10.dp), text = it.paragraphe_animal
                )
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = it.height,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Pink80)
                    )
                    Text(
                        text = it.weight,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Box(modifier = Modifier
            .size(40.dp)
            .background(Purple80))
    }
}

@Composable
fun AnimalImages2(imagePath: String) {
    AsyncImage(
        model = imagePath,
        contentDescription = "Image d'un animal",
        modifier = Modifier,
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun preview(){
//    WikiAnimalsDetails()
}