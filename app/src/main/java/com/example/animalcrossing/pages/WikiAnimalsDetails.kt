package com.example.animalcrossing.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animalcrossing.R
import com.example.animalcrossing.ui.theme.PinkPrincipal
import com.example.animalcrossing.ui.theme.backgroundColor

class Animal( name: String, secondName: String, about: String, size: Number, weight: Number, age: Number, regime: String, eatAdAy: Number, type: String, carac: Array<String>, condition: Array<String>)

@Composable
fun WikiAnimalsDetails(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)){
        ImageDetails()
        Text(modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(PinkPrincipal).padding(10.dp),text = "Ce sont des animaux qui se caractérisent par la mobilité indépendante de leurs yeux, leur langue protractile qui leur permet d'attraper leurs proies à distance, les doigts groupés en deux blocs opposabl")
        Box(modifier = Modifier.size(40.dp).background(PinkPrincipal))
    }

}

@Composable
fun ImageDetails(modifier: Modifier = Modifier){
    Image(painter = painterResource(id = R.drawable.cameleon_casque), contentDescription = "image animal", modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop)
}

@Preview(showBackground = true)
@Composable
fun preview(){
    WikiAnimalsDetails()
}