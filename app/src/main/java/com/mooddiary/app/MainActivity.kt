package com.mooddiary.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mooddiary.app.elements.BottomBar
import com.mooddiary.app.elements.TopBar
import com.mooddiary.app.model.BottomBarItem
import com.mooddiary.app.model.DataStoreManager
import com.mooddiary.app.model.advicesList
import com.mooddiary.app.screens.advices.AdviceItemOverview
import com.mooddiary.app.screens.advices.AdvicesScreen
import com.mooddiary.app.screens.authorize.AuthorizeScreen
import com.mooddiary.app.screens.calendar.CalendarScreen
import com.mooddiary.app.screens.change.ChangePasswordScreen
import com.mooddiary.app.screens.creation.CreateAccountScreen
import com.mooddiary.app.screens.delete.DeleteAccountScreen
import com.mooddiary.app.screens.login.LoginScreen
import com.mooddiary.app.screens.settings.SettingsScreen
import com.mooddiary.app.screens.splash.SplashScreen
import com.mooddiary.app.screens.statistics.StatisticsScreen
import com.mooddiary.app.ui.theme.MoodDiaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        enableEdgeToEdge()
        setContent {
            MoodDiaryTheme {
                MoodDiaryAppEntryPoint(this)
            }
        }
    }
}

@Composable
fun MoodDiaryAppEntryPoint(
    context: Context
) {
    val navController = rememberNavController()
    val dataStoreManager = DataStoreManager(context)

    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalDataStoreManager provides dataStoreManager
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            startDestination = SplashRoute
        ) {
            composable(SplashRoute) { SplashScreen() }
            composable(MainAppRoute) { MoodDiaryApp() }
            composable(LoginRoute) { LoginScreen() }
            composable(CreateRoute) { CreateAccountScreen() }
            composable(AdviceCountRoute) { AdviceItemOverview(item =  advicesList[0]) }
            composable(AdvicePuzzleRoute) { AdviceItemOverview(item =  advicesList[1]) }
            composable(AdviceResourcesRoute) { AdviceItemOverview(item =  advicesList[2]) }
            composable(AdviceRoudeRoute) { AdviceItemOverview(item =  advicesList[3]) }
            composable(AdviceSleepRoute) { AdviceItemOverview(item =  advicesList[4]) }
            composable(DeleteAccountRoute) { DeleteAccountScreen() }
            composable(ChangePasswordRoute) { ChangePasswordScreen() }
            composable(AuthorizeRoute) { AuthorizeScreen() }
        }
    }
}

@Composable
fun MoodDiaryApp() {
    var bottomBarState by rememberSaveable {
        mutableStateOf<BottomBarItem>(BottomBarItem.Calendar)
    }

    Scaffold(
        topBar = { TopBar(title = bottomBarState.title, withBackHandler = false) },
        bottomBar = { BottomBar(changeState = { clickedItem ->
            bottomBarState = clickedItem
        }, item = bottomBarState) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when(bottomBarState) {
                BottomBarItem.Calendar -> CalendarScreen()
                BottomBarItem.Advices -> AdvicesScreen()
                BottomBarItem.Statistics -> StatisticsScreen()
                BottomBarItem.Settings -> SettingsScreen()
            }
        }
    }
}
