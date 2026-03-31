package com.runique.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runique.auth.domain.AuthRepository
import com.runique.auth.domain.UserDataValidator
import com.runique.core.domain.util.DataError
import com.runique.core.domain.util.Result
import com.runique.core.presentation.ui.R
import com.runique.core.presentation.ui.UiText
import com.runique.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

//TODO : needs testing
class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            combine(
                snapshotFlow { state.email.text.toString() },
                snapshotFlow { state.password.text.toString() }) { email, password ->
                state.copy(
                    isEmailValid = userDataValidator.isValidEmail(email),
                    passwordValidationState = userDataValidator.validatePassword(password),
                    canRegister = userDataValidator.isValidEmail(email) &&
                            userDataValidator.validatePassword(password).isValidPassword
                )
            }.collect { newState ->
                state = newState
            }
        }
    }

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnLoginClick -> {
                //TODO : navigate to login screen
            }
            else -> Unit
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = repository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isRegistering = false)
            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(
                            RegisterEvent.RegistrationError(UiText.StringResource(R.string.error_email_already_exists))
                        )
                    } else {
                        eventChannel.send(RegisterEvent.RegistrationError(result.error.asUiText()))
                    }
                }

                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}