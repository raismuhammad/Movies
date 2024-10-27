package com.rais.movies.utils

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request

class HeaderRemovalInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        // Build a new request without the specific header
        val modifiedRequest = originalRequest.newBuilder()
            .removeHeader("x-amz-cf-id") // Remove the specific header
            .removeHeader("x-amz-cf-pop") // Remove the specific header
            .removeHeader("x-amz-cf-id") // Remove the specific header
            .build()
        return chain.proceed(modifiedRequest)
    }
}