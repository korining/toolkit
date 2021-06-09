package com.korning.flyhttp.error

/**
 * Created by ninglongfei.zhao on 2021/3/1.
 *
 */
class RemoteException : RuntimeException {
    var code: String? = null
        private set

    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(code: String?, message: String?) : super(message) {
        this.code = code
    }

    constructor(code: String?, message: String?, cause: Throwable?) : super(
        message,
        cause
    ) {
        this.code = code
    }

}