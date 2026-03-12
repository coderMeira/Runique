package com.runique.auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runique.auth.domain.PasswordValidationState
import com.runique.auth.domain.UserDataValidator
import com.runique.core.presentation.designsystem.CheckIcon
import com.runique.core.presentation.designsystem.CrossIcon
import com.runique.core.presentation.designsystem.EmailIcon
import com.runique.core.presentation.designsystem.Poppins
import com.runique.core.presentation.designsystem.R
import com.runique.core.presentation.designsystem.RuniqueDarkRed
import com.runique.core.presentation.designsystem.RuniqueGray
import com.runique.core.presentation.designsystem.RuniqueGreen
import com.runique.core.presentation.designsystem.RuniqueTheme
import com.runique.core.presentation.designsystem.components.GradientBackground
import com.runique.core.presentation.designsystem.components.RuniqueActionButton
import com.runique.core.presentation.designsystem.components.RuniquePasswordTextField
import com.runique.core.presentation.designsystem.components.RuniqueTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoot(
    onSignInClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    RegisterScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.create_account),
                style = MaterialTheme.typography.headlineMedium
            )
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Poppins,
                        color = RuniqueGray
                    )
                ) {
                    append(stringResource(id = R.string.already_have_an_account) + " ")
                    pushStringAnnotation(
                        tag = "LOGIN",
                        annotation = stringResource(id = R.string.login)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontFamily = Poppins,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(stringResource(id = R.string.login))
                    }
                }
            }
            Text(
                text = annotatedString,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        onAction(RegisterAction.OnLoginClick)
                    }
            )
            Spacer(modifier = Modifier.height(48.dp))
            RuniqueTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = if (state.isEmailValid) CheckIcon else null,
                hint = stringResource(id = R.string.email_hint),
                title = stringResource(id = R.string.email),
                modifier = Modifier.fillMaxWidth(),
                additionalInfo = stringResource(id = R.string.email_additional_info),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            RuniquePasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = { onAction(RegisterAction.OnTogglePasswordVisibilityClick) },
                hint = stringResource(id = R.string.password),
                title = stringResource(id = R.string.password),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordRequirementItem(
                text = stringResource(
                    id = R.string.password_requirement_length,
                    UserDataValidator.MIN_PASSWORD_LENGTH
                ),
                isMet = state.passwordValidationState.hasMinLength
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirementItem(
                text = stringResource(id = R.string.password_number_required),
                isMet = state.passwordValidationState.hasDigit
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirementItem(
                text = stringResource(id = R.string.password_lowercase_required),
                isMet = state.passwordValidationState.hasLowerCase
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirementItem(
                text = stringResource(id = R.string.password_uppercase_required),
                isMet = state.passwordValidationState.hasUpperCase
            )
            Spacer(modifier = Modifier.height(32.dp))
            RuniqueActionButton(
                text = stringResource(id = R.string.register),
                isLoading = state.isRegistering,
                enabled = state.canRegister,
                onClick = { onAction(RegisterAction.OnRegisterClick) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun PasswordRequirementItem(
    text: String,
    isMet: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isMet) CheckIcon else CrossIcon,
            contentDescription = null,
            tint = if (isMet) RuniqueGreen else RuniqueDarkRed
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RuniqueTheme {
        RegisterScreen(
            state = RegisterState(
                passwordValidationState = PasswordValidationState(
                    hasUpperCase = true,
                    hasLowerCase = true,
                    hasDigit = true,
                    hasMinLength = false
                )
            ),
            onAction = {}
        )
    }
}