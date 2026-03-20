package com.runique.auth.data.di

import com.runique.auth.data.EmailPatternValidator
import com.runique.auth.domain.PatternValidator
import com.runique.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> { EmailPatternValidator }
    singleOf(::UserDataValidator)
}