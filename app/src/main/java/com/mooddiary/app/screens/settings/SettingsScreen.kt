package com.mooddiary.app.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mooddiary.app.ChangePasswordRoute
import com.mooddiary.app.DeleteAccountRoute
import com.mooddiary.app.LocalDataStoreManager
import com.mooddiary.app.LocalNavController
import com.mooddiary.app.LoginRoute
import com.mooddiary.app.MainAppRoute
import com.mooddiary.app.SplashRoute
import com.mooddiary.app.elements.AppButton
import com.mooddiary.app.screens.calendar.CalendarScreenViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: SettingsScreenViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val dataStoreManager = LocalDataStoreManager.current
    val coroutine = rememberCoroutineScope()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        AppButton(
            title = "Змінити пароль",
            onClick = {
                navController.navigate(ChangePasswordRoute)
            },
            enabled = true
        )
        AppButton(
            title = "Видалити акаунт",
            onClick = {
                navController.navigate(DeleteAccountRoute)
            },
            enabled = true
        )
        AppButton(
            title = "Вийти з акаунту",
            onClick = {
                coroutine.launch {
                    val userData = dataStoreManager.getUserData().first()
                    val updatedUserData = userData.copy(
                        isAuthorize = false
                    )
                    dataStoreManager.saveUserData(updatedUserData)

                    navController.navigate(LoginRoute) {
                        popUpTo(MainAppRoute) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            },
            enabled = true
        )
    }
}
