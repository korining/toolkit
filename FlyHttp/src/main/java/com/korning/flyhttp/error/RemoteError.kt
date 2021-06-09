package com.korning.flyhttp.error

/**
 * Created by ninglongfei.zhao on 2021/3/1.
 *
 */
class RemoteError {
    var code: String? = null
    var error: String? = null

    constructor(code: String?, error: String?) {
        this.code = code
        this.error = error
    }
}