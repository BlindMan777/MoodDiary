package com.mooddiary.app.screens.statistics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mooddiary.app.data.SavedMoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StatisticsScreenViewModel @Inject constructor(
    private val repository: SavedMoodsRepository
) : ViewModel() {

    private val _moodTypeState = MutableStateFlow("good_mood")
    val moodTypeState: StateFlow<String> = _moodTypeState

    val moodsList = repository.getAllMoods().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun changeMoodType(moodType: String) {
        _moodTypeState.update { moodType }
    }
}
