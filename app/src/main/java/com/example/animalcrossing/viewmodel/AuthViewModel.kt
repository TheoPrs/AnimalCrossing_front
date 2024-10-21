import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animalcrossing.pages.AccueilPage
import com.example.loginpage.LoginScreen
import com.example.registerpage.RegisterScreen

@Composable
fun NavigationApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController) // Écran de connexion
        }
        composable("register") {
            RegisterScreen(navController) // Écran de création de compte
        }
        composable("home") {
            AccueilPage()
        }
    }
}
fun loginEmail(email: String, password: String, navController: NavController, context: Context) {
    if(email == "azer" && password == "azer") {
        navController.navigate("home")
    } else if(email.isEmpty() || password.isEmpty()) {
        Toast.makeText(context, "Vous devez renseigner chaque champ.", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Mail ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
    }
}