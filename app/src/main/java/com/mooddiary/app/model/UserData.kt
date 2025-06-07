package com.mooddiary.app.model

data class UserData(
    val email: String,
    val password: String,
    val isAuthorize: Boolean,
    val isAccountCreated: Boolean
)
