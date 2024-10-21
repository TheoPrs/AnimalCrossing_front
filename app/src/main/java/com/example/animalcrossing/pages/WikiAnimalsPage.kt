package com.example.animalcrossing.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.animalcrossing.data.entities.AnimalWiki
import com.example.animalcrossing.viewmodels.AnimalWikiListViewModel

@Composable
fun AnimalsBox(
    modifier: Modifier = Modifier, onNavigationClick: () -> Unit,
    animal: AnimalWiki
){
    Box(modifier = modifier
        .width(300.dp)
        .height(50.dp)
        .shadow(8.dp, RoundedCornerShape(16.dp))
        .clip(RoundedCornerShape(16.dp))
        .background(Color(0xFFEAC9B8))
        .clickable(onClick = onNavigationClick)
    ){
        AnimalImages(animal.image_path)
        Text(text = animal.name, modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = 150.dp), fontFamily = FontFamily.Serif
        )
    }
}

@Composable
fun AnimalImages(imagePath: String) {
    AsyncImage(
        model = imagePath,
        contentDescription = "Image d'un animal",
        modifier = Modifier
            .size(100.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
    )
}


@Composable
fun WikiAnimalsPage(navController: NavController) {
    val animalViewModel: AnimalWikiListViewModel = viewModel()
    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF333333))) {
        SearchBar(
            hint = "Entrer le nom d'un animal...",
            onTextChange = { searchText = it }
        )

        val filteredAnimals = animalViewModel.animals.filter {
            it.name.contains(searchText, ignoreCase = true)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(filteredAnimals.size) { index ->
                val animal = filteredAnimals[index]
                AnimalsBox(
                    modifier = Modifier.padding(vertical = 9.dp),
                    onNavigationClick = {
                        navController.navigate("details/${animal.id_animal}")
                    },
                    animal
                )
            }
        }
    }
}

@Composable
fun SearchBar(hint: String, onTextChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(350.dp)
                .height(50.dp)
                .background(Color(0xFFEAC9B8), CircleShape),
            contentAlignment = Alignment.Center
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
                    onValueChange = {
                        text = it
                        onTextChange(it)
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                )
            }

            if (text.isEmpty()) {
                androidx.compose.material3.Text(
                    text = hint,
                    style = TextStyle(color = Color(0xFF755F4E), fontSize = 16.sp),
                    modifier = Modifier.padding(start = 50.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMyScreen() {
    val navController = rememberNavController()
    WikiAnimalsPage(navController)
}
//
//@Preview(showBackground = true)
//@Composable
//fun animalComponentPreview()
//{
//    val navController = rememberNavController()
//    AnimalsBox(onNavigationClick = {
//
//    })
//}