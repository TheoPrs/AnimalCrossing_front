package com.example.animalcrossing


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.animalcrossing.pages.WikiAnimalsDetails
import com.example.animalcrossing.pages.WikiAnimalsPage
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


val supabase = createSupabaseClient(
    supabaseUrl = "https://hkrzmotogcwjlwmdrmni.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imhrcnptb3RvZ2N3amx3bWRybW5pIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcyOTI5NTI1NSwiZXhwIjoyMDQ0ODcxMjU1fQ.K88vxBuzCDGeYrT8E28JE1fSEQkkxGa5LNmMc9VQK_k"
) {
    install(Auth)
    install(Postgrest)
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
//            UserList()
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


@Composable
fun NavigationGraph(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { AccueilPage(context = context) }
        composable("search") { WikiAnimalsPage(navController) }
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

            AnimalPage(
                navController = navController,
                name = name,
                age = age,
                poids = poids,
                imageRes = imageRes
            )
        }
        composable("details/{animalId}") { backStackEntry ->
            val animalIdString =
                backStackEntry.arguments?.getString("animalId")
            val animalId =
                animalIdString?.toIntOrNull()
            Log.i("animalId", "${animalId}")
            if (animalId != null) {
                WikiAnimalsDetails(animalId)
            }
        }
    }
}



@Composable
fun SearchScreen() {

}




