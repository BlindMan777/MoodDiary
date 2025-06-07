package com.mooddiary.app.screens.delete

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mooddiary.app.LocalDataStoreManager
import com.mooddiary.app.LocalNavController
import com.mooddiary.app.LoginRoute
import com.mooddiary.app.MainAppRoute
import com.mooddiary.app.elements.AppButton
import com.mooddiary.app.elements.CustomTextField
import com.mooddiary.app.elements.TopBar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun DeleteAccountScreen(
    viewModel: DeleteAccountViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val dataStoreManager = LocalDataStoreManager.current
    val coroutine = rememberCoroutineScope()

    val typedPasswordState = viewModel.passwordState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(title = "Видалення акаунту", withBackHandler = true)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            CustomTextField(
                value = typedPasswordState.value,
                placeholder = "Підтвердіть пароль",
                password = true,
                onValueChange = { newValue ->
                    viewModel.updatePasswordTextField(newValue)
                }
            )
            Spacer(Modifier.height(20.dp))
            AppButton(
                title = "Видалити акаунт",
                onClick = {
                    coroutine.launch {
                        val userData = dataStoreManager.getUserData().first()
                        val userPassword = userData.password

                        if(userPassword == typedPasswordState.value) {
                            dataStoreManager.clearUserData()
                            navController.navigate(LoginRoute) {
                                popUpTo(MainAppRoute) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    }
                },
                enabled = true
            )
        }
    }
}
