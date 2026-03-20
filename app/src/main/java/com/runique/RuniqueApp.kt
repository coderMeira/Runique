package com.runique

import android.app.Application
import com.runique.auth.data.di.authDataModule
import com.runique.auth.presentation.di.AuthViewModelModule
import com.runique.di.appModule
import com.runique.runique.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RuniqueApp)
            modules(
                appModule,
                authDataModule,
                AuthViewModelModule
            )
        }
    }
}
