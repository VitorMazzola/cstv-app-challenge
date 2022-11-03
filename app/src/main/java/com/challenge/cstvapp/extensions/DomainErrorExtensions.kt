package com.challenge.cstvapp.extensions

import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.UnknownHostException

internal fun <T> Result<T>.getOrThrowDomainError(): T = getOrElse { throwable ->
    throw throwable.toDomainError()
}

internal fun Throwable.toDomainError(): Throwable {
    return when (this) {
        is HttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_NOT_FOUND ->
                    NotFoundException("Ops! Não conseguimos encontrar as partidas :'(")
                else -> UnexpectedException()
            }
        }
        else -> this
    }
}