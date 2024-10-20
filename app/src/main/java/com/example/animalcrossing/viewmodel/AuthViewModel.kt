// LoginUtils.kt

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController

fun loginEmail(email: String, password: String, navController: NavController, context: Context) {
    if(email == "azer@gmail.com" && password == "azer1234") {
        navController.navigate("home")
    } else if(email.isEmpty() || password.isEmpty()) {
        Toast.makeText(context, "Vous devez renseigner chaque champ.", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Mail ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
    }
}