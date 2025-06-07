package com.mooddiary.app.screens.change

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChangePasswordScreenViewModel @Inject constructor() : ViewModel() {

    private val _typedUserPasswordState = MutableStateFlow("")
    val typedUserPasswordState: StateFlow<String> = _typedUserPasswordState
    private val _newPasswordState = MutableStateFlow("")
    val newPasswordState: StateFlow<String> = _newPasswordState
    private val _confirmNewPasswordState = MutableStateFlow("")
    val confirmNewPasswordState: StateFlow<String> = _confirmNewPasswordState

    fun updateTypedUserPasswordState(newValue: String) {
        _typedUserPasswordState.update { newValue }
    }

    fun updateNewPasswordState(newValue: String) {
        _newPasswordState.update { newValue }
    }

    fun updateConfirmNewPasswordState(newValue: String) {
        _confirmNewPasswordState.update { newValue }
    }
}
