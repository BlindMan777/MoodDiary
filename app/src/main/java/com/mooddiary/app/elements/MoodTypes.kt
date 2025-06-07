package com.mooddiary.app.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mooddiary.app.R

@Composable
fun MoodTypes(
    modifier: Modifier = Modifier,
    enabledClick: Boolean,
    onClick: (moodType: String) -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable(
                    enabled = enabledClick,
                    onClick = { onClick("great_mood") }
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(R.drawable.great_icon),
                contentDescription = "greate_icon"
            )
            Text(
                text = "— Почуваюся чудово",
                color = Color.Green.copy(alpha = 0.9f),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable(
                    enabled = enabledClick,
                    onClick = { onClick("good_mood") }
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(R.drawable.good_icon),
                contentDescription = "good_icon"
            )
            Text(
                text = "— Мені добре",
                color = Color.Yellow.copy(alpha = 0.9f),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable(
                    enabled = enabledClick,
                    onClick = { onClick("neutral_mood") }
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(R.drawable.neutral_icon),
                contentDescription = "neutral_icon"
            )
            Text(
                text = "— Нейтральний настрій",
                color = Color.LightGray,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable(
                    enabled = enabledClick,
                    onClick = { onClick("bad_mood") }
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(R.drawable.bad_icon),
                contentDescription = "bad_icon"
            )
            Text(
                text = "— Кепсько себе почуваю",
                color = Color.Red,
            )
        }
    }
}