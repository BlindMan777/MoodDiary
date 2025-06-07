package com.mooddiary.app

import android.app.Application
import com.mooddiary.app.data.MainDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    val dataBase by lazy { MainDataBase.createDataBase(this) }
}
