package com.runique.auth.domain

import com.runique.core.domain.util.DataError
import com.runique.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}