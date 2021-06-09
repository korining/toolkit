package com.korning.flyhttp.common

import com.korning.flyhttp.error.ErrorHandler
import com.korning.flyhttp.error.RemoteException
import kotlinx.coroutines.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by ninglongfei.zhao on 2021/3/2.
 * 包含了presenter中 发起请求&数据解析部分的封装
 */
abstract class BaseRepository {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    abstract fun launchError(error: String)

    fun launch(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope
            .launch(
                CoroutineExceptionHandler { coroutineContext, throwable ->
                    ErrorHandler.handle(throwable).message?.let { launchError(it) }
                })
            {
                block.invoke(this)
            }
    }

    suspend fun <T : Any> load(response: T): Result<T> {
        return coroutineScope {
                Result.Success(response)
        }
    }

    suspend fun <T : Any> request(request: T): Result<T> {
        return try {
            execute(request)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(ErrorHandler.handle(e))
        }
    }

    private suspend fun <T : Any> execute(
        response: T,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {
        return coroutineScope {
                successBlock?.let { it() }
                Result.Success(response)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        viewModelJob.cancel()
    }
}