package com.runique.auth.domain

data class PasswordValidationState(
    val hasUpperCase: Boolean = false,
    val hasLowerCase: Boolean = false,
    val hasDigit: Boolean = false,
    val hasMinLength: Boolean = false
) {
    val isValidPassword: Boolean
        get() = hasUpperCase && hasLowerCase && hasDigit && hasMinLength
}
