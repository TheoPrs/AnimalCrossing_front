package com.example.animalcrossing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animalcrossing.components.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()  // Crée un contrôleur de navigation

    MaterialTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }  // Passe le navController au BottomNavigationBar
        ) { contentPadding ->
            Surface(
                modifier = Modifier
                    .padding(contentPadding)  // Assure que le contenu ne chevauche pas la BottomBar
            ) {
                NavigationGraph(navController = navController)  // Appelle le NavHost pour gérer les routes
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("search") { SearchScreen() }
        composable("profile") { ProfileScreen() }
    }
}

@Composable
fun HomeScreen() {
    // Contenu de ton écran d'accueil
}

@Composable
fun SearchScreen() {
    // Contenu de ton écran de recherche
}

@Composable
fun ProfileScreen() {
    // Contenu de ton écran de profil
}
