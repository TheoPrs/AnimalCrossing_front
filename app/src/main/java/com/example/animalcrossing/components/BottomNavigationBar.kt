package com.example.animalcrossing.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource  // Ajout de l'import
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.example.animalcrossing.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme


@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableIntStateOf(0) }  // Mise à jour pour mutableIntStateOf

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Blue
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 }
        )
        // Icône centrale pour la patte de chien
        BottomNavigationItem(
            icon = {
                Box(modifier = Modifier.height(56.dp).padding(bottom = 8.dp)) {  // Ajustement de l'icône centrale
                    Icon(painterResource(id = R.drawable.paw), contentDescription = "Patte de chien")
                }
            },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            alwaysShowLabel = false  // Ne montre pas le label pour l'icône centrale
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profil") },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}
