package com.korning.flyhttp.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by ninglongfei.zhao on 2021/3/1.
 *
 */
class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Content-Type", "application/x-www-form-urlencoded")
        builder.addHeader("Connection", "keep-alive")
        return chain.proceed(builder.build())
    }
}