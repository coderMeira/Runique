package com.example.core.domain.util

sealed interface Result<out D, out Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : com.example.core.domain.util.Error>(val error: E) : Result<Nothing, E>
}

inline fun <T, E : Error> Result<T, E>.map(transform: (T) -> T): Result<T, E> = when (this) {
    is Result.Success -> Result.Success(transform(data))
    is Result.Error -> this
}