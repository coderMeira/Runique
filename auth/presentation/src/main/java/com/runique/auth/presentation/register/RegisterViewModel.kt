package com.runique.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runique.auth.domain.UserDataValidator
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

//TODO : needs testing
class RegisterViewModel(
    private val userDataValidator: UserDataValidator
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    init {
        viewModelScope.launch {
            combine(
                snapshotFlow { state.email.text.toString() },
                snapshotFlow { state.password.text.toString() }
            ) { email, password ->
                state.copy(
                    isEmailValid = userDataValidator.isValidEmail(email),
                    passwordValidationState = userDataValidator.validatePassword(password)
                )
            }.collect { state = it }
        }
    }

    fun onAction(action: RegisterAction) { // TODO: handle actions

    }
}