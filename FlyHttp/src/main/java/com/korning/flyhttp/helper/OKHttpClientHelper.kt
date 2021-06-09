package com.korning.flyhttp.helper

import android.util.Log
import com.korning.flyhttp.common.NetConstants
import com.korning.flyhttp.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by ninglongfei.zhao on 2021/3/1.
 *
 */
object OKHttpClientHelper {

    fun create(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(NetConstants.TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
        builder.readTimeout(NetConstants.TIMEOUT_READ, TimeUnit.MILLISECONDS)
        builder.writeTimeout(NetConstants.TIMEOUT_WRITE, TimeUnit.MILLISECONDS)

        builder.addInterceptor(HeaderInterceptor())//header
        builder.addInterceptor(HttpLoggingInterceptor(object :
            HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                if (NetConstants.printLog) {
                    Log.d(NetConstants.TAG, message)
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY))

        return builder.build()
    }
}