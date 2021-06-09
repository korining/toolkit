package com.korning.flyhttp.common

import com.korning.flyhttp.BuildConfig


/**
 * Created by ninglongfei.zhao on 2021/3/1.
 *
 */
object NetConstants {
    const val TAG = "OKHttp"

    const val TIMEOUT_CONNECT = 20 * 1000L
    const val TIMEOUT_READ = 20 * 1000L
    const val TIMEOUT_WRITE = 20 * 1000L

    var printLog = BuildConfig.DEBUG
}