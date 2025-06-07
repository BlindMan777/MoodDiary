package com.mooddiary.app.screens.creation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.mooddiary.app.model.UserData
import kotlinx.coroutines.launch

@Composable
fun CreateAccountScreen(
    viewModel: CreateAccountViewModel = hiltViewModel(),
) {

    val navController = LocalNavController.current
    val dataStoreManager = LocalDataStoreManager.current
    val coroutine = rememberCoroutineScope()

    val emailState = viewModel.emailState.collectAsStateWithLifecycle()
    val passwordState = viewModel.passwordState.collectAsStateWithLifecycle()
    val confirmPasswordState = viewModel.confirmPasswordState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(title = "Створення акаунту", withBackHandler = false)
        Spacer(Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(28.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                CustomTextField(
                    value = emailState.value,
                    placeholder = "Електронна пошта",
                    onValueChange = { newValue ->
                        viewModel.updateEmailTextField(newValue)
                    },
                    password = false
                )
                CustomTextField(
                    value = passwordState.value,
                    placeholder = "Пароль",
                    onValueChange = { newValue ->
                        viewModel.updatePasswordTextField(newValue)
                    },
                    password = true
                )
                CustomTextField(
                    value = confirmPasswordState.value,
                    placeholder = "Повторіть пароль",
                    onValueChange = { newValue ->
                        viewModel.updateConfirmPasswordTextField(newValue)
                    },
                    password = true
                )
            }
            Spacer(Modifier.height(20.dp))
            AppButton(
                title = "Створити акаунт",
                onClick = {
                    if (!emailState.value.isEmpty() && !passwordState.value.isEmpty() && !confirmPasswordState.value.isEmpty() && passwordState.value == confirmPasswordState.value) {
                        coroutine.launch {
                            dataStoreManager.saveUserData(
                                UserData(
                                    email = emailState.value,
                                    password = passwordState.value,
                                    isAuthorize = true,
                                    isAccountCreated = true
                                )
                            )
                        }
                        navController.navigate(MainAppRoute) {
                            popUpTo(LoginRoute) {
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
}
