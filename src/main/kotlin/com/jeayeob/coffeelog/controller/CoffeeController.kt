package com.jeayeob.coffeelog.controller

import com.jeayeob.coffeelog.dto.CoffeeCreateRequest
import com.jeayeob.coffeelog.dto.CoffeeResponse
import com.jeayeob.coffeelog.dto.CoffeeUpdateRequest
import com.jeayeob.coffeelog.service.CoffeeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/coffee")
class CoffeeController(
    private val coffeeService: CoffeeService
) {

    @GetMapping
    fun getAllCoffee(): List<CoffeeResponse> {
        return coffeeService.getAllCoffee()
    }

    @GetMapping("/{name}")
    fun getCoffeeByName(@PathVariable name: String): CoffeeResponse {
        return coffeeService.getCoffeeByName(name)
    }

    @PostMapping
    fun saveCoffee(@RequestBody request: CoffeeCreateRequest) : CoffeeResponse {
        return coffeeService.saveCoffee(request)
    }

    @PutMapping
    fun updateCoffee(@RequestBody request: CoffeeUpdateRequest) : CoffeeResponse {
        return coffeeService.updateCoffee(request)
    }

    @DeleteMapping
    fun deleteCoffee(@RequestParam id: Long) {
        coffeeService.deleteCoffee(id)
    }

}