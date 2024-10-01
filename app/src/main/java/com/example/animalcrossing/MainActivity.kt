package com.example.animalcrossing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.animalcrossing.components.BottomNavigationBar

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
    MaterialTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar() }
        ) { contentPadding: PaddingValues ->

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)  // Apply padding here
            ) {

            }
        }
    }
}
