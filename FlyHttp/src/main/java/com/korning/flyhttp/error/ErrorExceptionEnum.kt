package com.korning.flyhttp.error

/**
 * Created by ninglongfei.zhao on 2021/3/1.
 *
 */
enum class ErrorExceptionEnum(var code: String, var describe: String) {

    Other("1000", "接口请求异常."),
    UnknownHostException("1001", "域名解析异常."),
    SocketTimeoutException("1002", "网络连接超时."),
    ConnectException("1003", "网络连接异常."),
    JsonParseException("1004", "接口数据解析异常.");

    override fun toString(): String {
        return "code:$code  description:$describe"
    }
}