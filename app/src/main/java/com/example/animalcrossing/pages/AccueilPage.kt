package com.example.animalcrossing.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import com.example.animalcrossing.R

@Composable
fun AccueilPage() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Titre de la page
        Text(
            text = "Accueil",
            style = TextStyle(
                color = Color(0xFFEAC9B8),
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 16.dp)
        )


        SearchBar(
            hint = "Search for an animal"
        )


        Text(
            text = "Le narval, souvent surnommé \"licorne des mers\", possède une longue défense torsadée qui peut atteindre jusqu'à 3 mètres de long. Ce n'est pas une corne, mais une dent géante !",
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(top = 40.dp),
            lineHeight = 24.sp
        )
    }
}

@Composable
fun SearchBar(hint: String) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .height(50.dp)
            .background(Color(0xFFEAC9B8), CircleShape),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            // Icone de recherche
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF755F4E),
                modifier = Modifier.size(20.dp)
            )


            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )


            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color(0xFF755F4E),
                modifier = Modifier.size(24.dp)
            )
        }


        if (text.isEmpty()) {
            Text(
                text = hint,
                style = TextStyle(color = Color(0xFF755F4E), fontSize = 16.sp),
                modifier = Modifier.padding(start = 40.dp)
            )
        }
    }
}
