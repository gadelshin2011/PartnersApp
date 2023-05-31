package com.example.partnersapp.presentation.network

import okhttp3.Interceptor
import okhttp3.Response

class JSONHeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("withAuth", "false")
            .addHeader("AppKey", "sCNXJoLWDSchbDRouriS")
            .build()
        return chain.proceed(request)
    }
}