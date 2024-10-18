package com.example.animalcrossing


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animalcrossing.components.BottomNavigationBar
import com.example.animalcrossing.components.HeadBar
import com.example.animalcrossing.pages.AccueilPage
import com.example.animalcrossing.pages.ProfilePage
import com.example.loginpage.LoginScreen
import com.example.registerpage.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    MaterialTheme {
        Scaffold(
            topBar = { HeadBar(navController = navController) },
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding)
            ) {
                NavigationGraph(navController = navController)
            }
        }
    }
}
//to do : Faire la transition
@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { AccueilPage() }
        composable("search") { SearchScreen() }
        composable("profile") { ProfilePage(navController) }
    }
}

@Composable
fun SearchScreen() {

}

