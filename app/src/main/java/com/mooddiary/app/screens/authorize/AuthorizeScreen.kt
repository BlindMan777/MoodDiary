package com.mooddiary.app.screens.authorize

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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun AuthorizeScreen(
    viewModel: AuthorizeScreenViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val dataStoreManager = LocalDataStoreManager.current
    val coroutine = rememberCoroutineScope()

    val emailState = viewModel.emailState.collectAsStateWithLifecycle()
    val passwordState = viewModel.passwordState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(title = "Авторизація", withBackHandler = false)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                CustomTextField(
                    value = emailState.value,
                    placeholder = "Електронна пошта",
                    password = false,
                    onValueChange = { newValue ->
                        viewModel.updateEmailTextField(newValue)
                    }
                )
                CustomTextField(
                    value = passwordState.value,
                    placeholder = "Пароль",
                    password = true,
                    onValueChange = { newValue ->
                        viewModel.updatePasswordTextField(newValue)
                    }
                )
            }
            Spacer(Modifier.height(20.dp))
            AppButton(
                title = "Увійти",
                enabled = true,
                onClick = {
                    coroutine.launch {
                        val userData = dataStoreManager.getUserData().first()
                        if(userData.email == emailState.value && userData.password == passwordState.value) {
                            dataStoreManager.saveUserData(
                                dataStoreManager.getUserData().first().copy(
                                    isAuthorize = true
                                )
                            )
                            navController.navigate(MainAppRoute) {
                                popUpTo(LoginRoute) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }
    }
}
