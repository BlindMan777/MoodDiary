package com.mooddiary.app.model

import android.os.Parcelable
import com.mooddiary.app.R
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class BottomBarItem(val iconID: Int, val title: String, val descr: Int) : Parcelable {
    object Calendar: BottomBarItem(R.drawable.calendar_icon, "Календар", R.string.calendar)
    object Advices: BottomBarItem(R.drawable.advice_icon, "Поради для тебе", R.string.advices)
    object Statistics: BottomBarItem(R.drawable.statistics_icon, "Статистика", R.string.statistics)
    object Settings: BottomBarItem(R.drawable.setting_icon, "Налаштування", R.string.settings)
}

val bottomBarItems = listOf(
    BottomBarItem.Calendar,
    BottomBarItem.Advices,
    BottomBarItem.Statistics,
    BottomBarItem.Settings
)
