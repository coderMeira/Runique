package com.runique.auth.domain

class UserDataValidator(
    private val patternValidator: PatternValidator
) {

    fun isValidEmail(email: String): Boolean {
        return patternValidator.matches(email.trim())
    }

    fun validatePassword(password: String): PasswordValidationState {
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH

        return PasswordValidationState(
            hasUpperCase = hasUpperCase,
            hasLowerCase = hasLowerCase,
            hasDigit = hasDigit,
            hasMinLength = hasMinLength
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 9
    }
}