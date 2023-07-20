package com.jeayeob.coffeelog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoffeeLogApplication

fun main(args: Array<String>) {
    runApplication<CoffeeLogApplication>(*args)
}
