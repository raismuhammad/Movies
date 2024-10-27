package com.rais.movies.utils

import android.util.Log
import com.rais.movies.di.AuthModule
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val  authToken: String // Token dapat berasal dari tempat penyimpanan
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $authToken") // Ganti dengan skema autentikasi Anda
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}