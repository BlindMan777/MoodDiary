package com.mooddiary.app.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.mooddiary.app.ui.theme.greateVibesFontFamily

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontFamily = greateVibesFontFamily,
            color = MaterialTheme.colorScheme.surface
        )
    }
}
