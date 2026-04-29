package com.runique.run.presentation.di

import com.runique.run.presentation.runoverview.RunOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val runOverviewViewModelModule = module {
    viewModelOf(::RunOverviewViewModel)
}
