package com.example.animalcrossing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.tooling.preview.Preview
import com.example.animalcrossing.R

import androidx.compose.material3.*
import androidx.navigation.NavController

import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(width = 1.dp, color = Color.Gray)
        ) {
            BottomNavigation(
                backgroundColor = Color(0xFF333333),
                contentColor = Color(0xFFCCB7A8),
                modifier = Modifier.fillMaxWidth()
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color(0xFFD4BFA7)) },
                    selected = currentDestination == "search",
                    onClick = { navController.navigate("search") },
                    selectedContentColor = Color(0xFFD4BFA7),
                    unselectedContentColor = Color(0xFFCCB7A8)
                )
                Spacer(modifier = Modifier.weight(1f)) // Spacer pour centrer l'icône de patte
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color(0xFFD4BFA7)) },
                    selected = currentDestination == "profile",
                    onClick = { navController.navigate("profile") },
                    selectedContentColor = Color(0xFFD4BFA7),
                    unselectedContentColor = Color(0xFFCCB7A8)
                )
            }
        }


        Box(
            modifier = Modifier
                .size(110.dp)
                .offset(y = (-20).dp)
                .clip(CircleShape)
                .background(Color(0xFF333333))
                .clickable { navController.navigate("home") }
                .border(width = 1.dp, color = Color.Gray, shape = CircleShape),

            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.paw),
                contentDescription = "Dog Paw",
                tint = Color(0xFFAEA3B7),
                modifier = Modifier.size(65.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {

}