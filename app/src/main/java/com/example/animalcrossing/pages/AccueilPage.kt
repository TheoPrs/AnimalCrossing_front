package com.example.animalcrossing.pages

import AnimalFactViewModel
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import com.example.animalcrossing.R
import com.example.animalcrossing.components.AnimalCard
import com.example.animalcrossing.components.AnimalList
import com.example.animalcrossing.data.datasource.animal.FeedingStatusManager

@Composable
fun AccueilPage(context: Context, viewModel: AnimalFactViewModel = viewModel()) {

    val animalFact by viewModel.animalFact.collectAsState()

    val animals = listOf(
        AnimalCard(name = "FouFou", hasEatenToday = false, imageRes = R.drawable.image_foufou),
        AnimalCard(name = "Doudou", hasEatenToday = false, imageRes = R.drawable.image_rex)
    )

    val calendar = Calendar.getInstance()
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    val daysOfWeek = listOf("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY")
    val currentDay = daysOfWeek[dayOfWeek - 1]



    val feedingStatusManager = remember { FeedingStatusManager(context) }

    var updatedAnimals by remember { mutableStateOf(animals) }

    updatedAnimals = animals.map { animal ->
        val hasEatenToday by feedingStatusManager.getFedStatus(currentDay).collectAsState(initial = false)
        animal.copy(hasEatenToday = hasEatenToday)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        animalFact?.let { fact ->
            Text(
                text = fact,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(top = 40.dp)
            )
        } ?: run {
            Text(
                text = "Loading...",
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp)
            )
        }


        AnimalList(animals = updatedAnimals)
    }
}

