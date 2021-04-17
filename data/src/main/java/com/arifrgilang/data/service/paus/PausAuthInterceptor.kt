package com.arifrgilang.data.service.paus

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/**
 * Created by arifrgilang on 4/14/2021
 */
class PausAuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
            .header("Authorization", authToken)
        val request: Request = builder.build()
        return chain.proceed(request)
    }
}