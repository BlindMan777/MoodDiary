package com.mooddiary.app.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mooddiary.app.LocalDataStoreManager
import com.mooddiary.app.data.SavedMoods
import com.mooddiary.app.elements.MoodPieChart
import com.mooddiary.app.elements.MoodTypes
import com.mooddiary.app.screens.calendar.CalendarScreenViewModel
import com.mooddiary.app.ui.theme.greateVibesFontFamily

@Composable
fun StatisticsScreen (
    viewModel: StatisticsScreenViewModel = hiltViewModel()
) {

    val moodTypeState = viewModel.moodTypeState.collectAsState()
    val savedMoods = viewModel.moodsList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Кількість днів по настрою",
                fontSize = 28.sp,
                fontFamily = greateVibesFontFamily,
                color = MaterialTheme.colorScheme.surface
            )
            MoodPieChart(
                savedMoods = savedMoods.value,
                selectedMood = moodTypeState.value
            )
        }
        Spacer(Modifier.height(20.dp))
        MoodTypes(
            modifier = Modifier.height(180.dp),
            enabledClick = true,
            onClick = { mood ->
                viewModel.changeMoodType(mood)
            }
        )
    }
}





