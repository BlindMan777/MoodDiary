package com.mooddiary.app.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.mooddiary.app.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mooddiary.app.LocalDataStoreManager
import com.mooddiary.app.LocalNavController
import com.mooddiary.app.LoginRoute
import com.mooddiary.app.MainAppRoute
import com.mooddiary.app.SplashRoute
import com.mooddiary.app.ui.theme.greateVibesFontFamily
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {

    val navController = LocalNavController.current
    val dataStoreManager = LocalDataStoreManager.current

    LaunchedEffect(Unit) {
        dataStoreManager.getUserData().collect { data ->
            val isUserAuthorized = data.isAuthorize
            val routeAfterCheck = if (isUserAuthorized) MainAppRoute else LoginRoute

            delay(1500L)

            navController.navigate(routeAfterCheck) {
                popUpTo(SplashRoute) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.splash_background_img),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(200.dp))
            Text(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = "Mood Diary",
                fontSize = 44.sp,
                fontFamily = greateVibesFontFamily,
                color = MaterialTheme.colorScheme.surface,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}
