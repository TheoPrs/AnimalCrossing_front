package com.example.loginpage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animalcrossing.pages.AccueilPage
import com.example.registerpage.RegisterScreen
import loginEmail

class LoginPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Crée le contrôleur de navigation
            NavigationApp(navController)
        }
    }
}
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
            AccueilPage(navController)
        }
    }
}
@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = viewModel()) {
    val mainColor = Color(0xFFEAC9B8)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    // Naviguer vers l'écran de création de compte
                    navController.navigate("register")
                },
                colors = ButtonDefaults.buttonColors(mainColor),
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(text = "Créer un compte")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Animal Crossing",
                style = MaterialTheme.typography.h3.copy(
                    color = mainColor,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(text = "Connexion", style = MaterialTheme.typography.h4, color = mainColor)

            Spacer(modifier = Modifier.height(60.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = mainColor,
                    unfocusedBorderColor = mainColor,
                    focusedLabelColor = mainColor,
                    focusedBorderColor = mainColor
                ),
                textStyle = TextStyle(color = Color.White),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Mot de passe") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = mainColor,
                    unfocusedBorderColor = mainColor,
                    focusedLabelColor = mainColor,
                    focusedBorderColor = mainColor
                ),
                textStyle = TextStyle(color = Color.White),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    loginEmail(email, password, navController,context)
                },
                colors = ButtonDefaults.buttonColors(mainColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Connexion")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (loginState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = if (loginState.errorMessage.isNotBlank()) loginState.errorMessage else "",
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}