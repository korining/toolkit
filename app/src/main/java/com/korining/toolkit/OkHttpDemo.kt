package com.korining.toolkit

import android.util.Log
import okhttp3.*
import java.io.IOException

/**
 * Created by ninglongfei.zhao on 2020/10/23.
 *
 */
class OkHttpDemo {

    val url = "https://github.com/square/okhttp"

    private fun okHttpGet(): String? {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        var response: Response? = null
        try {
            response = okHttpClient.newCall(request).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return response?.body?.string()
    }

    private fun okHttpSysGet() {
        val url = "http://wwww.baidu.com"
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .get() //默认就是GET请求，可以不写
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("okHttp", "onFailure: ")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log.d("okHttp", "onResponse: " + response.body!!.string())
            }
        })
    }
}