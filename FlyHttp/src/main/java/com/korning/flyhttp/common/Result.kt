package com.korning.flyhttp.common

/**
 * Created by ninglongfei.zhao on 2021/3/1.
 *
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T?) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }

    fun onSuccess(onSuccess: (T) -> Unit): Result<T> {
        if (this is Success<*>)
            onSuccess.invoke(data as T)
        return this
    }

    fun onError(onError: (String?) -> Unit): Result<T> {
        if (this is Error)
            onError.invoke(exception.message)
        return this
    }
}