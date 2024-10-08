package com.example.animalcrossing.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animalcrossing.components.AnimalsBox
import com.example.animalcrossing.ui.theme.backgroundColor

@Composable
fun WikiAnimalsPage(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(50) { index ->
                AnimalsBox(modifier = Modifier.padding(vertical = 9.dp), onNavigationClick = {
                    navController.navigate("details/$index")
                })
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