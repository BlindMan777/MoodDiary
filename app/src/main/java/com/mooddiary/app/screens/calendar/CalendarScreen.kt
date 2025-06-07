package com.mooddiary.app.screens.calendar

import android.app.Dialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mooddiary.app.R
import com.mooddiary.app.data.SavedMoods
import com.mooddiary.app.elements.AppButton
import com.mooddiary.app.elements.Divider
import com.mooddiary.app.elements.MoodTypes
import com.mooddiary.app.ui.theme.greateVibesFontFamily
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.Month

@Composable
fun CalendarScreen(
    viewModel: CalendarScreenViewModel = hiltViewModel()
) {
    val moodsList = viewModel.moodsList.collectAsState(initial = emptyList())
    val selectedMood = remember { mutableStateOf<SavedMoods?>(null) }
    val selectedDate = remember { mutableStateOf<String?>(null) }


    val date = remember {
        mutableStateOf(LocalDate.now())
    }

    val year = date.value.year

    val month = when (date.value.month) {
        Month.JANUARY -> "Січень"
        Month.FEBRUARY -> "Лютий"
        Month.MARCH -> "Березень"
        Month.APRIL -> "Квітень"
        Month.MAY -> "Травень"
        Month.JUNE -> "Червень"
        Month.JULY -> "Липень"
        Month.AUGUST -> "Серпень"
        Month.SEPTEMBER -> "Вересень"
        Month.OCTOBER -> "Жовтень"
        Month.NOVEMBER -> "Листопад"
        Month.DECEMBER -> "Грудень"
    }

    // Кількість днів у поточному місяці
    val daysInMonth = date.value.lengthOfMonth()

    // День тижня для першого числа місяця (1 = Monday, 7 = Sunday)
    val firstDayOfMonth = date.value.withDayOfMonth(1).dayOfWeek.value

    // Створюємо список з порожніх комірок перед 1 числом (щоб вирівняти)
    val calendarDays = buildList {
        repeat((firstDayOfMonth - 1).coerceAtLeast(0)) { add(null) } // null — порожня клітинка
        addAll((1..daysInMonth).toList())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$month $year",
                    fontSize = 28.sp,
                    fontFamily = greateVibesFontFamily,
                    color = MaterialTheme.colorScheme.surface
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable(
                                onClick = {
                                    date.value = date.value.minusMonths(1)
                                }
                            ),
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.surface
                    )
                    Icon(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable(
                                onClick = {
                                    date.value = date.value.plusMonths(1)
                                }
                            ),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.surface
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Пн",
                    fontSize = 24.sp,
                    fontFamily = greateVibesFontFamily,
                    color = MaterialTheme.colorScheme.surface
                )
                Text(
                    text = "Вт",
                    fontSize = 24.sp,
                    fontFamily = greateVibesFontFamily,
                    color = MaterialTheme.colorScheme.surface
                )
                Text(
                    text = "Ср",
                    fontSize = 24.sp,
                    fontFamily = greateVibesFontFamily,
                    color = MaterialTheme.colorScheme.surface
                )
                Text(
                    text = "Чт",
                    fontSize = 24.sp,
                    fontFamily = greateVibesFontFamily,
                    color = MaterialTheme.colorScheme.surface
                )
                Text(
                    text = "Пт",
                    fontSize = 24.sp,
                    fontFamily = greateVibesFontFamily,
                    color = MaterialTheme.colorScheme.surface
                )
                Text(
                    text = "Сб",
                    fontSize = 24.sp,
                    fontFamily = greateVibesFontFamily,
                    color = MaterialTheme.colorScheme.surface
                )
                Text(
                    text = "Нд",
                    fontSize = 24.sp,
                    fontFamily = greateVibesFontFamily,
                    color = MaterialTheme.colorScheme.surface
                )
            }

            Spacer(Modifier.height(12.dp))
            Divider()
            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = GridCells.Fixed(7),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                userScrollEnabled = false // не треба скрол, бо всього один місяць
            ) {
                items(calendarDays.size) { index ->
                    val day = calendarDays[index]

                    val currentDate = day?.let {
                        val dateObj = date.value.withDayOfMonth(it)
                        dateObj.toString()
                    }

                    val moodColor = moodsList.value.find { it.date == currentDate }?.mood?.let { mood ->
                        when(mood) {
                            "great_mood" -> Color.Green
                            "good_mood" -> Color.Yellow
                            "neutral_mood" -> Color.LightGray
                            "bad_mood" -> Color.Red
                            else -> MaterialTheme.colorScheme.tertiary
                        }
                    } ?: MaterialTheme.colorScheme.tertiary

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f) // квадратна клітинка
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (day != null) MaterialTheme.colorScheme.primary else Color.Transparent)
                            .clickable(
                                enabled = day != null,
                                onClick = {
                                    currentDate?.let {
                                        val existingMood = moodsList.value.find { it.date == currentDate }
                                        selectedDate.value = it
                                        selectedMood.value = existingMood
                                    }
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (day != null) {
                            Text(
                                text = day.toString(),
                                fontSize = 16.sp,
                                color = moodColor
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        MoodTypes(
            modifier = Modifier.fillMaxSize(),
            enabledClick = false,
            onClick = {}
        )
    }

    CalendarCardDialog(
        date = selectedDate.value,
        onDismissClick = { selectedDate.value = null },
        viewModel = viewModel,
        selectedMoods = selectedMood.value
    )
}

@Composable
fun CalendarCardDialog(
    date: String?,
    onDismissClick: () -> Unit,
    viewModel: CalendarScreenViewModel,
    selectedMoods: SavedMoods?
) {
    val coroutine = rememberCoroutineScope()

    if (date == null) return
    Dialog(
        onDismissRequest = onDismissClick,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 5.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Оберіть свій настрій цього дня",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Center,
                    fontFamily = greateVibesFontFamily
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable(
                                onClick = {
                                    coroutine.launch {
                                        viewModel.insertItem(
                                            selectedMoods?.copy(
                                                date = date,
                                                mood = "great_mood"
                                            ) ?: SavedMoods(
                                                date = date,
                                                mood = "great_mood"
                                            )
                                        )
                                    }

                                    onDismissClick()
                                }
                            ),
                        painter = painterResource(R.drawable.great_icon),
                        contentDescription = "great_icon"
                    )
                    Image(
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                onClick = {
                                    coroutine.launch {
                                        viewModel.insertItem(
                                            selectedMoods?.copy(
                                                date = date,
                                                mood = "good_mood"
                                            ) ?: SavedMoods(
                                                date = date,
                                                mood = "good_mood"
                                            )
                                        )
                                    }

                                    onDismissClick()
                                }
                            ),
                        painter = painterResource(R.drawable.good_icon),
                        contentDescription = "good_icon"
                    )
                    Image(
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                onClick = {
                                    coroutine.launch {
                                        viewModel.insertItem(
                                            selectedMoods?.copy(
                                                date = date,
                                                mood = "neutral_mood"
                                            ) ?: SavedMoods(
                                                date = date,
                                                mood = "neutral_mood"
                                            )
                                        )
                                    }

                                    onDismissClick()
                                }
                            ),
                        painter = painterResource(R.drawable.neutral_icon),
                        contentDescription = "neutral_icon"
                    )
                    Image(
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                onClick = {
                                    coroutine.launch {
                                        viewModel.insertItem(
                                            selectedMoods?.copy(
                                                date = date,
                                                mood = "bad_mood"
                                            ) ?: SavedMoods(
                                                date = date,
                                                mood = "bad_mood"
                                            )
                                        )
                                    }

                                    onDismissClick()
                                }
                            ),
                        painter = painterResource(R.drawable.bad_icon),
                        contentDescription = "bad_icon"
                    )
                }
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = "Видалити",
                    enabled = true,
                    onClick = {
                        coroutine.launch {
                            selectedMoods?.let {
                                viewModel.deleteItem(it)
                            }
                        }

                        onDismissClick()
                    }
                )
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = "Закрити",
                    enabled = true,
                    onClick = onDismissClick
                )
            }
        }
    }
}
