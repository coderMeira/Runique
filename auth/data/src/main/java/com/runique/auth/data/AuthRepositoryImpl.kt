package com.runique.auth.data

import com.runique.auth.domain.AuthRepository
import com.runique.core.data.networking.post
import com.runique.core.domain.AuthInfo
import com.runique.core.domain.SessionStorage
import com.runique.core.domain.util.DataError
import com.runique.core.domain.util.EmptyResult
import com.runique.core.domain.util.Result
import com.runique.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        )
        if (result is Result.Success) {
            val authInfo = AuthInfo(
                accessToken = result.data.accessToken,
                refreshToken = result.data.refreshToken,
                userId = result.data.userId,
            )
            sessionStorage.set(authInfo)
        }
        return result.asEmptyDataResult()
    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(email, password)
        )
    }
}