package com.mooddiary.app.screens.delete

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DeleteAccountViewModel @Inject constructor() : ViewModel() {

    private val _passwordState = MutableStateFlow("")
    val passwordState: StateFlow<String> = _passwordState

    fun updatePasswordTextField(newValue: String) {
        _passwordState.update { newValue }
    }
}
