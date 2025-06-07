package com.mooddiary.app.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mooddiary.app.data.MainDataBase
import com.mooddiary.app.data.SavedMoods
import com.mooddiary.app.data.SavedMoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarScreenViewModel @Inject constructor(
    private val repository: SavedMoodsRepository
): ViewModel() {

    val moodsList = repository.getAllMoods().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun insertItem(mood: SavedMoods) {
        viewModelScope.launch {
            repository.insertMood(mood)
        }
    }

    fun deleteItem(mood: SavedMoods) {
        viewModelScope.launch {
            repository.deleteMood(mood)
        }
    }

    fun deleteItemByDate(date: String) {
        viewModelScope.launch {
            val mood = repository.getMoodByDate(date)
            mood?.let {
                repository.deleteMood(it)
            }
        }
    }
}
