package com.example.animalcrossing


import android.os.Bundle
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animalcrossing.components.BottomNavigationBar
import com.example.animalcrossing.components.HeadBar
import com.example.animalcrossing.pages.AccueilPage
import com.example.animalcrossing.pages.AnimalPage
import com.example.animalcrossing.pages.ProfilePage

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

        composable(
            "animalPage/{name}/{age}/{poids}/{imageRes}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("age") { type = NavType.IntType },
                navArgument("poids") { type = NavType.IntType },
                navArgument("imageRes") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val age = backStackEntry.arguments?.getInt("age") ?: 0
            val poids = backStackEntry.arguments?.getInt("poids") ?: 0
            val imageRes = backStackEntry.arguments?.getInt("imageRes")

            AnimalPage(navController = navController, name = name, age = age, poids = poids, imageRes = imageRes)
        }
    }
}


@Composable
fun SearchScreen() {

}

