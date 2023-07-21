package com.jeayeob.coffeelog.controller

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.jeayeob.coffeelog.exception.MessageException
import com.jeayeob.coffeelog.response.ExceptionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionController : ResponseEntityExceptionHandler() {

    @ExceptionHandler(MessageException::class)
    internal fun handleMessageException(msg: MessageException): ResponseEntity<ExceptionResponse<ErrorMessage, Map<String, *>>> {

        val error = ErrorMessage(message = msg.message, data = msg.data)
        val exceptionResponse = ExceptionResponse<ErrorMessage, Map<String, *>>(
            requestId = "requestId",
            code = msg.resultCode.code,
            error = error
        )

        return ResponseEntity.ok(exceptionResponse)
    }

    internal class ErrorMessage(
        @JsonInclude(NON_ABSENT)
        val message: String?,

        @JsonInclude(NON_NULL)
        val data: Map<String, Any>? = null
    )
}