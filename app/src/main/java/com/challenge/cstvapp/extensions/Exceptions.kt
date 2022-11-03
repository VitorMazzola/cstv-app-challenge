package com.challenge.cstvapp.extensions

import java.io.IOException

sealed class HttpException(
    message: String? = null,
    cause: Throwable? = null
) : Throwable(message, cause)

class NotFoundException(message: String?): HttpException(message)
class UnexpectedException : HttpException()
class NoInternetException: IOException()