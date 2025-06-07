package com.mooddiary.app.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mooddiary.app.LocalNavController
import com.mooddiary.app.ui.theme.greateVibesFontFamily

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    withBackHandler: Boolean
) {
    val navController = LocalNavController.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.secondary),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(withBackHandler) {
                Icon(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(
                            onClick = {
                                navController.popBackStack()
                            }
                        ),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "turn_back",
                    tint = MaterialTheme.colorScheme.surface
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end =  if (withBackHandler) 22.dp else 0.dp),
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontFamily = greateVibesFontFamily,
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}
