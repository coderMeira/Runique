package com.runique.auth.presentation.di

import com.runique.auth.presentation.login.LoginViewModel
import com.runique.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val AuthViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}
