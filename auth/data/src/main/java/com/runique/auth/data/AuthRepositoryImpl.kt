package com.runique.auth.data

import com.runique.auth.domain.AuthRepository
import com.runique.core.data.networking.post
import com.runique.core.domain.util.DataError
import com.runique.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
) : AuthRepository {
    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(email, password)
        )
    }
}