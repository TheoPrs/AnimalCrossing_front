package com.example.registerpage

import android.os.Bundle
import android.widget.Space
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
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animalcrossing.pages.AccueilPage
import com.example.loginpage.LoginScreen

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
fun RegisterScreen(navController: NavHostController, viewModel: RegisterViewModel = viewModel()) {
    val mainColor = Color(0xFFEAC9B8)
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isEmailValid by remember{ mutableStateOf(true) }
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    val registerState by viewModel.registerState.collectAsState()
    val errorColor = Color.Green
    val borderColor = if (password == password2 || password2.isEmpty()) mainColor else errorColor
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                navController.navigate("login")
            },
            colors = ButtonDefaults.buttonColors(mainColor),
            modifier = Modifier.padding(end = 16.dp).align(Alignment.End).height(30.dp) // Padding to move the button away from the edge
        ) {
            Text(text = "Se connecter")
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Animal Crossing",
            style = MaterialTheme.typography.h3.copy(
                color = Color(0xFFEAC9B8),
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "Inscription", style = MaterialTheme.typography.h4, color = mainColor)

        Spacer(modifier = Modifier.height(60.dp))
        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = { Text("Nom de famille") },
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
            modifier = Modifier.width(400.dp)
                .padding(32.dp,0.dp,32.dp,0.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Prénom ") },
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
            modifier = Modifier.width(400.dp)
                .padding(32.dp,0.dp,32.dp,0.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
        // Email input
        OutlinedTextField(
            value = email,
            onValueChange = { email = it
                            isEmailValid = it.matches(Regex(emailPattern))
                            },
            label = { Text("Email") },
            isError = !isEmailValid,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedLabelColor = mainColor,
                unfocusedBorderColor = if (isEmailValid) mainColor else errorColor,
                focusedLabelColor = mainColor,
                focusedBorderColor = if (isEmailValid) mainColor else errorColor
            ),
            textStyle = TextStyle(color = Color.White),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.width(400.dp)
                .padding(32.dp,0.dp,32.dp,0.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password input
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
            modifier = Modifier.width(400.dp)
                .padding(32.dp,0.dp,32.dp,0.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password input
        OutlinedTextField(
            value = password2,
            onValueChange = { password2 = it },
            label = { Text("Confirmer mot de passe") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedLabelColor = mainColor,
                unfocusedBorderColor = borderColor,
                focusedLabelColor = mainColor,
                focusedBorderColor = borderColor
            ),
            textStyle = TextStyle(color = Color.White),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.width(400.dp)
                .padding(32.dp,0.dp,32.dp,0.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        val context = LocalContext.current
        Button(
            onClick = {
                if (password.toString() == password2.toString()) {
                    viewModel.register(surname, name, email, password, password2)
                }else {
                    Toast.makeText(context, "Les mots de passe ne sont pas identiques.",Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(mainColor),
            modifier = Modifier.fillMaxWidth().padding(32.dp,0.dp,32.dp,25.dp)
        ) {
            Text(text = "Inscription")
        }


        // Display login status
        if (registerState.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(
                text = if (registerState.errorMessage.isNotBlank()) registerState.errorMessage else "",
                color = MaterialTheme.colors.error
            )
        }
    }
    }