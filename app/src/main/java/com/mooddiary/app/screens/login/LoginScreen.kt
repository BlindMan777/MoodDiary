package com.mooddiary.app.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mooddiary.app.AuthorizeRoute
import com.mooddiary.app.CreateRoute
import com.mooddiary.app.LocalDataStoreManager
import com.mooddiary.app.LocalNavController
import com.mooddiary.app.R
import com.mooddiary.app.elements.AppButton
import com.mooddiary.app.model.UserData
import com.mooddiary.app.ui.theme.greateVibesFontFamily
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = hiltViewModel(),
) {
    val navController = LocalNavController.current
    val dataStoreManager = LocalDataStoreManager.current


    val isAccountCreated = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        isAccountCreated.value = dataStoreManager.getUserData().first().isAccountCreated
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 68.dp)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .width(280.dp),
            painter = painterResource(R.drawable.login_vector),
            contentDescription = "login",
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = "Вам потрібно увійти в акаунт, або створити, якщо він ще не створений",
                fontSize = 20.sp,
                fontFamily = greateVibesFontFamily,
                color = MaterialTheme.colorScheme.surface
            )
            Spacer(Modifier.height(12.dp))
            AppButton(
                title = "Увійти",
                onClick = {
                    navController.navigate(AuthorizeRoute)
                },
                enabled = isAccountCreated.value
            )
            Spacer(Modifier.height(12.dp))
            AppButton(
                title = "Створити акаунт",
                onClick = {
                    navController.navigate(CreateRoute)
                },
                enabled = !isAccountCreated.value
            )
            Spacer(Modifier.height(36.dp))
        }
    }
}
