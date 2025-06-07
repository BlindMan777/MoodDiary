package com.mooddiary.app.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mooddiary.app.R
import com.mooddiary.app.ui.theme.greateVibesFontFamily

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    password: Boolean,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by rememberSaveable {
        mutableStateOf(!password)
    }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder, color = Color.Gray, fontFamily = greateVibesFontFamily, fontSize = 16.sp)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.tertiary,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (password) {
                val icon = if (passwordVisible) R.drawable.password_invisible_icon else R.drawable.password_visible_icon
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = "Toggle Password Visibility",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    )
}
