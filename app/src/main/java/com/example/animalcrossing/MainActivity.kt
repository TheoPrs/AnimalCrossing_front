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
import com.example.animalcrossing.pages.AccueilPage

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
    val navController = rememberNavController()

    MaterialTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { contentPadding ->
            Surface(
                modifier = Modifier
                    .padding(contentPadding)
            ) {
                NavigationGraph(navController = navController)
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { AccueilPage() }
        composable("search") { SearchScreen() }
        composable("profile") { ProfileScreen() }
    }
}

@Composable
fun SearchScreen() {

}

@Composable
fun ProfileScreen() {

}
