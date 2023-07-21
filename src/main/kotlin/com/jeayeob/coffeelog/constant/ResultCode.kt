package com.jeayeob.coffeelog.constant

import com.jeayeob.coffeelog.exception.IResultCode

enum class ResultCode(override val code: Int, override val message: String) : IResultCode {
    /**
     * Common Result Code
     */
    OK(Code.OK, Message.OK),
    GENERAL_ERROR(Code.GENERAL_ERROR, Message.GENERAL_ERROR),
    NOT_IMPLEMENTED(Code.NOT_IMPLEMENTED, Message.NOT_IMPLEMENTED),
    BAD_PARAMETERS(Code.BAD_PARAMETERS, Message.BAD_PARAMETERS),
    PERMISSION_DENIED(Code.PERMISSION_DENIED, Message.PERMISSION_DENIED),
    UNAUTHENTICATED_USER(Code.UNAUTHENTICATED_USER, Message.UNAUTHENTICATED_USER),

    /**
     * Custom Result Code
     */
    COFFEE_NOT_FOUND(Code.COFFEE_NOT_FOUND, Message.COFFEE_NOT_FOUND);



    object Code {
        const val OK = 0
        const val GENERAL_ERROR = 1
        const val NOT_IMPLEMENTED = 2
        const val BAD_PARAMETERS = 3
        const val PERMISSION_DENIED = 4
        const val UNAUTHENTICATED_USER = 6

        const val COFFEE_NOT_FOUND = 10001
    }

    object Message {
        const val OK = "Success"
        const val GENERAL_ERROR = "General Error"
        const val NOT_IMPLEMENTED = "Not Implemented"
        const val BAD_PARAMETERS = "Bad Parameters"
        const val PERMISSION_DENIED = "Permission Denied"
        const val UNAUTHENTICATED_USER = "Unauthenticated User"

        const val COFFEE_NOT_FOUND = "커피를 찾을 수 없습니다."
    }
}