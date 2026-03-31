package com.runique.core.data.di

import com.runique.core.data.auth.EncryptedSessionStorage
import com.runique.core.data.networking.HttpClientFactory
import com.runique.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}
