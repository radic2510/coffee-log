package com.jeayeob.coffeelog.exception

import com.jeayeob.coffeelog.constant.ResultCode

open class MessageException(
    val resultCode: IResultCode = ResultCode.GENERAL_ERROR,
    override var message: String? = null,
    val data: Map<String, Any>? = null,
) : RuntimeException(message) {

    init {
        if (this.message == null) {
            this.message = resultCode.message
        }
    }
}