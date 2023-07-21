package com.jeayeob.coffeelog.dto

import com.jeayeob.coffeelog.constant.Processing
import com.jeayeob.coffeelog.entity.Coffee

data class CoffeeCreateRequest(
    val name: String,
    var country: String? = null,
    var address: String? = null,
    var variety: String? = null,
    val processing: Processing? = null,
) {
    fun toEntity() = Coffee(
        name = name,
        country = country,
        address = address,
        variety = variety,
        processing = processing,
    )
}