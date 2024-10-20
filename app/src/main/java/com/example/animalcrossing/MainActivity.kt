package com.example.animalcrossing


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animalcrossing.components.BottomNavigationBar
import com.example.animalcrossing.components.HeadBar
import com.example.animalcrossing.data.entities.AnimalWiki
import com.example.animalcrossing.data.entities.User
import com.example.animalcrossing.pages.AccueilPage
import com.example.animalcrossing.pages.ProfilePage
import com.example.loginpage.LoginScreen
import com.example.registerpage.RegisterScreen
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val supabase = createSupabaseClient(
    supabaseUrl = "https://hkrzmotogcwjlwmdrmni.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imhrcnptb3RvZ2N3amx3bWRybW5pIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcyOTI5NTI1NSwiZXhwIjoyMDQ0ODcxMjU1fQ.K88vxBuzCDGeYrT8E28JE1fSEQkkxGa5LNmMc9VQK_k"
) {
    install(Auth)
    install(Postgrest)
    //install other modules
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

//to do : Faire la transition
@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { AccueilPage() }
        composable("search") { UserList() }
        composable("profile") { ProfilePage(navController) }
    }
}

@Composable
fun SearchScreen() {

}


@Composable
fun UserList(){
    val animals = remember{ mutableStateListOf<AnimalWiki>() }
    LaunchedEffect(Unit){
        withContext(Dispatchers.IO){
            val results = supabase.from("animal_wiki").select().decodeList<AnimalWiki>()
            Log.d("AnimalList", "Fetched users: $results")
            animals.addAll(results)
        }
    }
    LazyColumn {
        items(animals.size){ index ->
            val animal = animals[index]
            ListItem(headlineContent = { Text(text = animal.name) })
        }
    }
}

