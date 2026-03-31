package com.runique.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        androidApplication().getSharedPreferences("session_prefs", Context.MODE_PRIVATE)
    }
}
