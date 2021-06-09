package com.korning.flyhttp.error

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by ninglongfei.zhao on 2021/3/1.
 */
object ErrorHandler {

    private val gson = Gson()

    fun handle(e: Throwable?): RemoteException {
        var remoteException: RemoteException? = null
        when (e) {
            is HttpException -> {
                try {
                    val errorMsg = e.response()!!.errorBody()!!.string()
                    val remoteError =
                        gson.fromJson(
                            errorMsg,
                            RemoteError::class.java
                        )
                    remoteException = RemoteException(
                        remoteError.code,
                        remoteError.error
                    )
                    Log.e("remoteException", remoteException.toString())
                } catch (e1: Exception) {
                }
                if (remoteException == null) {
                    remoteException = RemoteException(
                        ErrorExceptionEnum.Other.code,
                        ErrorExceptionEnum.Other.describe
                    )
                }
            }
            is UnknownHostException -> {
                remoteException = RemoteException(
                    ErrorExceptionEnum.UnknownHostException.code,
                    ErrorExceptionEnum.UnknownHostException.describe
                )
            }
            is SocketTimeoutException -> {
                remoteException = RemoteException(
                    ErrorExceptionEnum.SocketTimeoutException.code,
                    ErrorExceptionEnum.SocketTimeoutException.describe
                )
            }
            is ConnectException -> {
                remoteException = RemoteException(
                    ErrorExceptionEnum.ConnectException.code,
                    ErrorExceptionEnum.ConnectException.describe
                )
            }
            is JsonSyntaxException -> {
                remoteException = RemoteException(
                    ErrorExceptionEnum.JsonParseException.code,
                    ErrorExceptionEnum.JsonParseException.describe
                )
            }
            else -> {
                remoteException = RemoteException(
                    ErrorExceptionEnum.Other.code,
                    ErrorExceptionEnum.Other.describe
                )
            }
        }
        return remoteException
    }
}