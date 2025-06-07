package com.mooddiary.app.screens.creation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor() : ViewModel() {

    private val _emailState = MutableStateFlow("")
    val emailState: StateFlow<String> = _emailState
    private val _passwordState = MutableStateFlow("")
    val passwordState: StateFlow<String> = _passwordState
    private val _confirmPasswordState = MutableStateFlow("")
    val confirmPasswordState: StateFlow<String> = _confirmPasswordState

    fun updateEmailTextField(newValue: String) {
        _emailState.update { newValue }
    }

    fun updatePasswordTextField(newValue: String) {
        _passwordState.update { newValue }
    }

    fun updateConfirmPasswordTextField(newValue: String) {
        _confirmPasswordState.update { newValue }
    }
}
