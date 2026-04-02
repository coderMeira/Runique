package com.runique.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runique.auth.domain.AuthRepository
import com.runique.auth.domain.UserDataValidator
import com.runique.auth.presentation.R
import com.runique.core.domain.util.DataError
import com.runique.core.domain.util.Result
import com.runique.core.presentation.ui.UiText
import com.runique.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userDataValidator: UserDataValidator
) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        val email = state.email.run { snapshotFlow { text } }
        val password = state.password.run { snapshotFlow { text } }
        combine(email, password) { emails, passwords ->
            state = state.copy(
                canLogin = userDataValidator.isValidEmail(emails.toString().trim()) &&
                        passwords.isNotEmpty()
            )
        }.launchIn(viewModelScope)
    }

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.OnTogglePasswordVisibility -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }

            LoginAction.OnLoginClick -> {
                login()
            }

            else -> Unit
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = authRepository.login(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isLoggingIn = false)

            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        eventChannel.send(
                            LoginEvent.Error(
                                UiText.StringResource(
                                    R.string.error_email_password_incorrect
                                )
                            )
                        )
                    } else {
                        eventChannel.send(LoginEvent.Error(result.error.asUiText()))
                    }
                }

                is Result.Success -> {
                    eventChannel.send(LoginEvent.LoginSuccess)
                }
            }
        }
    }
}