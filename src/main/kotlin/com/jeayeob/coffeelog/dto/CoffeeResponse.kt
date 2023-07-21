package com.jeayeob.coffeelog.dto

import com.jeayeob.coffeelog.constant.Processing
import com.jeayeob.coffeelog.entity.Coffee

data class CoffeeResponse(
    val name: String,
    val country: String?,
    val address: String?,
    val variety: String?,
    val processing: Processing?,
) {
    companion object {
        fun of(coffee: Coffee): CoffeeResponse {
            return CoffeeResponse(
                name = coffee.name,
                country = coffee.country,
                address = coffee.address,
                variety = coffee.variety,
                processing = coffee.processing
            )
        }
    }
}