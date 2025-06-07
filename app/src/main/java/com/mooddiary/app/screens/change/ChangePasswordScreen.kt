package com.mooddiary.app.screens.change

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.mooddiary.app.elements.AppButton
import com.mooddiary.app.elements.CustomTextField
import com.mooddiary.app.elements.TopBar
import com.mooddiary.app.model.UserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(
    viewModel: ChangePasswordScreenViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val dataStoreManager = LocalDataStoreManager.current
    val coroutine = rememberCoroutineScope()

    val typedPasswordState = viewModel.typedUserPasswordState.collectAsStateWithLifecycle()
    val newPasswordState = viewModel.newPasswordState.collectAsStateWithLifecycle()
    val confirmNewPasswordState = viewModel.confirmNewPasswordState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(title = "Зміна паролю", withBackHandler = true)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CustomTextField(
                value = typedPasswordState.value,
                placeholder = "Введіть свій пароль",
                password = true,
                onValueChange = { newValue ->
                    viewModel.updateTypedUserPasswordState(newValue)
                }
            )
            CustomTextField(
                value = newPasswordState.value,
                placeholder = "Введіть новий пароль",
                password = true,
                onValueChange = { newValue ->
                    viewModel.updateNewPasswordState(newValue)
                }
            )
            CustomTextField(
                value = confirmNewPasswordState.value,
                placeholder = "Підтвердіть новий пароль",
                password = true,
                onValueChange = { newValue ->
                    viewModel.updateConfirmNewPasswordState(newValue)
                }
            )
            AppButton(
                title = "Змінити пароль",
                onClick = {
                    coroutine.launch {
                        val userData = dataStoreManager.getUserData().first()
                        val userEmail = userData.email
                        val userPassword = userData.password

                        if (userPassword == typedPasswordState.value && newPasswordState.value == confirmNewPasswordState.value) {
                            dataStoreManager.saveUserData(
                                UserData(
                                    email = userEmail,
                                    password = newPasswordState.value,
                                    isAuthorize = true,
                                    isAccountCreated = true
                                )
                            )
                            navController.popBackStack()
                        }
                    }
                },
                enabled = true
            )
        }
    }
}
