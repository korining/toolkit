package com.korining.toolkit

import okhttp3.*
import org.junit.Test
import java.io.IOException

/**
 * Created by ninglongfei.zhao on 2020/10/23.
 *
 */
class OkHttpTest {

    @Test
    fun testOkHttp() {
        val url = "http://wwww.baidu.com"
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .get() //默认就是GET请求，可以不写
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("onFailure: $e")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                println("onResponse: " + response.body!!.string())
            }
        })

        Thread.sleep(10000)
    }
}