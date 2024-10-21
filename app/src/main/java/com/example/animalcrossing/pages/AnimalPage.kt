package com.example.animalcrossing.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.animalcrossing.data.datasource.animal.FeedingStatusManager



@Composable
fun AnimalPage(
    navController: NavHostController,
    name: String,
    age: Int,
    poids: Int,
    imageRes: Int?
) {
    val context = LocalContext.current
    val feedingStatusManager = remember { FeedingStatusManager(context) }
    val scope = rememberCoroutineScope()

    // Utilisation des collecteurs d'état pour lundi et mardi
    val mondayFeedingStatus by feedingStatusManager.getFedStatus("MONDAY").collectAsState(initial = false)
    val tuesdayFeedingStatus by feedingStatusManager.getFedStatus("TUESDAY").collectAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4D6CC))
            .padding(16.dp)
    ) {
        // Barre de retour
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Fleche retour",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Image de l'animal
        if (imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Animal Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .border(2.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Informations sur l'animal
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Age: $age, poids: $poids",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Statut de nourrissage pour les jours de la semaine
        AnimalFeedingStatus(
            name = name,
            mondayFeedingStatus = mondayFeedingStatus,
            tuesdayFeedingStatus = tuesdayFeedingStatus,
            onMondayStatusChanged = { fed ->
                scope.launch {
                    feedingStatusManager.setFedStatus("MONDAY", fed)
                }
            },
            onTuesdayStatusChanged = { fed ->
                scope.launch {
                    feedingStatusManager.setFedStatus("TUESDAY", fed)
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Bouton de vaccination
        VaccinationButton(
            onClick = {}
        )
    }
}

@Composable
fun AnimalFeedingStatus(
    name: String,
    mondayFeedingStatus: Boolean,
    tuesdayFeedingStatus: Boolean,
    onMondayStatusChanged: (Boolean) -> Unit,
    onTuesdayStatusChanged: (Boolean) -> Unit
) {
    val daysOfWeek = listOf("Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim")
    val feedingStatus = remember { Array(7) { mutableStateOf(false) } }

    // Synchroniser les statuts de nourrissage avec lundi et mardi
    feedingStatus[0].value = mondayFeedingStatus
    feedingStatus[1].value = tuesdayFeedingStatus

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF4D6CC), shape = MaterialTheme.shapes.medium)
    ) {
        Text(
            text = "Avez-vous nourri $name ?",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in daysOfWeek.indices) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    FeedingDayButton(
                        isFed = feedingStatus[i].value,
                        onClick = {
                            feedingStatus[i].value = !feedingStatus[i].value

                            // Appeler les fonctions de mise à jour de l'état pour lundi et mardi
                            when (i) {
                                0 -> onMondayStatusChanged(feedingStatus[0].value)
                                1 -> onTuesdayStatusChanged(feedingStatus[1].value)
                            }
                        }
                    )
                    Text(
                        text = daysOfWeek[i],
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}


@Composable
fun FeedingDayButton(isFed: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .clip(CircleShape)
            .background(
                color = if (isFed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isFed) "Oui" else "Non",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 12.sp
        )
    }
}

@Composable
fun VaccinationButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
    ) {
        Text(text = "Vaccination", color = MaterialTheme.colorScheme.onSecondary)
    }
}