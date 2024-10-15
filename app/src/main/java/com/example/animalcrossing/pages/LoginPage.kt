package com.example.loginpage

import android.os.Bundle
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class LoginPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val mainColor = Color(0xFFEAC9B8)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
        Text(text = "Connexion", style = MaterialTheme.typography.h4, color = mainColor)

        Spacer(modifier = Modifier.height(60.dp))

        // Email input
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
            modifier = Modifier.fillMaxWidth().padding(32.dp,0.dp,32.dp,0.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                viewModel.login(email, password)
            },
            colors = ButtonDefaults.buttonColors(mainColor),
            modifier = Modifier.fillMaxWidth().padding(32.dp,0.dp,32.dp,25.dp)
        ) {
            Text(text = "Connexion")
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Display login status
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