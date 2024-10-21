package com.example.animalcrossing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.animalcrossing.R

@Composable
fun AddAnimal(
    onAddAnimal: (name: String, age: Int, poids: Int, sexe: String, espece: String, imageRes: Int) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var poids by remember { mutableStateOf("") }
    var sexe by remember { mutableStateOf("Mâle") }
    var espece by remember { mutableStateOf("") }
    val imageRes = R.drawable.chien2


    Column(

        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color(0xFF333333))
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.White)
        )


        OutlinedTextField(
            value = age,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    age = newValue
                }
            },
            label = { Text("Âge", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = LocalTextStyle.current.copy(color = Color.White)
        )


        OutlinedTextField(
            value = poids,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    poids = newValue
                }
            },
            label = { Text("Poids", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = LocalTextStyle.current.copy(color = Color.White)
        )


        Row {
            RadioButton(
                selected = sexe == "Mâle",
                onClick = { sexe = "Mâle" }
            )
            Text("Mâle", color = Color.White)
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = sexe == "Femelle",
                onClick = { sexe = "Femelle" }
            )
            Text("Femelle", color = Color.White)
        }


        OutlinedTextField(
            value = espece,
            onValueChange = { espece = it },
            label = { Text("Espèce", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.White)
        )


        Row(

            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {

            Button(onClick = { onDismiss() },
                colors = ButtonDefaults.run { buttonColors(Color(0xFFEAC9B8))}
            ) {
                Text("Annuler")
            }
            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    val ageInt = age.toIntOrNull() ?: 0
                    val poidsInt = poids.toIntOrNull() ?: 0
                    onAddAnimal(name, ageInt, poidsInt, sexe, espece, imageRes)
                },
                colors = ButtonDefaults.run { buttonColors(Color(0xFFEAC9B8)) }
            ) {
                Text("Ajouter")
            }
        }
    }
}
