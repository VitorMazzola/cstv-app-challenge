package com.challenge.cstvapp.core

import android.content.Context
import android.net.ConnectivityManager
import com.challenge.cstvapp.BuildConfig
import com.challenge.cstvapp.extensions.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class OkHttpInteceptor(val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isConnected()) {
            throw NoInternetException()
        }

        val request = chain.request().newBuilder()
            .addHeader("authorization", BuildConfig.AUTHORIZATION)
            .build()

        return chain.proceed(request)
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isAvailable && netInfo.isConnected
    }
}