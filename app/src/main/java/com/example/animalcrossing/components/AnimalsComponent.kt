package com.example.animalcrossing.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animalcrossing.R
import com.example.animalcrossing.ui.theme.PinkPrincipal



@Composable
fun AnimalsBox(modifier: Modifier = Modifier, onNavigationClick: () -> Unit){
    Box(modifier = modifier
        .width(300.dp)
        .height(50.dp)
        .shadow(8.dp, RoundedCornerShape(16.dp))
        .clip(RoundedCornerShape(16.dp))
        .background(PinkPrincipal)
        .clickable(onClick = onNavigationClick)
    ){
        AnimalImages()
        Text(text = "Caméleon casqué", modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = 120.dp), fontFamily = FontFamily.Serif
        )
    }
}


@Composable
fun AnimalImages(){
    Image(painter = painterResource(id = R.drawable.cameleon_casque), contentDescription ="Image d'un caméléon", modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
fun animalComponentPreview()
{
    val navController = rememberNavController()
    AnimalsBox(onNavigationClick = {

    })
}