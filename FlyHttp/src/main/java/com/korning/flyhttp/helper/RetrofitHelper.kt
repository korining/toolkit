package com.korning.flyhttp.helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ninglongfei.zhao on 2021/3/1.
 *
 */
class RetrofitHelper<T> {

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OKHttpClientHelper.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun get(baseUrl: String, clazz: Class<T>): T {
        val retrofit = getRetrofit(baseUrl)
        return retrofit.create(clazz)
    }


}