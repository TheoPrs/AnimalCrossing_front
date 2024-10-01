package com.example.animalcrossing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.tooling.preview.Preview
import com.example.animalcrossing.R

@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableIntStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavigation(
            backgroundColor = Color(0xFF333333),
            contentColor = Color(0xFFCCB7A8),
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color(0xFFD4BFA7)) },
                selected = selectedItem == 0,
                onClick = { selectedItem = 0 },
                selectedContentColor = Color(0xFFD4BFA7),
                unselectedContentColor = Color(0xFFCCB7A8)
            )
            Spacer(modifier = Modifier.weight(1f)) // Spacer to center the paw icon
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color(0xFFD4BFA7)) },
                selected = selectedItem == 2,
                onClick = { selectedItem = 2 },
                selectedContentColor = Color(0xFFD4BFA7),
                unselectedContentColor = Color(0xFFCCB7A8)
            )
        }


        Box(
            modifier = Modifier
                .size(80.dp)
                .offset(y = (-10).dp)
                .clip(CircleShape)
                .background(Color(0xFF333333)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.paw),
                contentDescription = "Dog Paw",
                tint = Color(0xFFAEA3B7),
                modifier = Modifier.size(55.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}