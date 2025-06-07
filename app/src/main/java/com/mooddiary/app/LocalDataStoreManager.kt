package com.mooddiary.app

import androidx.compose.runtime.compositionLocalOf
import com.mooddiary.app.model.DataStoreManager

val LocalDataStoreManager = compositionLocalOf<DataStoreManager> {
    error("Can`t access NavController")
}
