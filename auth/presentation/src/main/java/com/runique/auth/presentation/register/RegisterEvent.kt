package com.runique.auth.presentation.register

import com.runique.core.presentation.ui.UiText

sealed interface RegisterEvent {
    data object RegistrationSuccess : RegisterEvent
    data class RegistrationError(val error: UiText) : RegisterEvent
}