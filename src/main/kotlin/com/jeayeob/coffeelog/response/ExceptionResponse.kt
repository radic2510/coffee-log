package com.jeayeob.coffeelog.response

import com.jeayeob.coffeelog.constant.ResultCode
import java.time.Instant

class ExceptionResponse<T, E>(
    val requestId: String,
    val code: Int = ResultCode.GENERAL_ERROR.code,
    val timestamp: Long = Instant.now().toEpochMilli(),
    val error: T?,
    val data: E? = null
) {

}