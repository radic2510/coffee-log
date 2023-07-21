package com.jeayeob.coffeelog.service

import com.jeayeob.coffeelog.constant.ResultCode
import com.jeayeob.coffeelog.dto.CoffeeCreateRequest
import com.jeayeob.coffeelog.dto.CoffeeResponse
import com.jeayeob.coffeelog.dto.CoffeeUpdateRequest
import com.jeayeob.coffeelog.exception.MessageException
import com.jeayeob.coffeelog.repository.CoffeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoffeeService (
    private val coffeeRepository: CoffeeRepository
) {
    fun getAllCoffee() : List<CoffeeResponse> {
        return coffeeRepository.findAll().map { CoffeeResponse.of(it) }
    }

    fun getCoffeeByName(name: String) : CoffeeResponse {
        return coffeeRepository.findByName(name)
            .map { CoffeeResponse.of(it) }
            .orElseThrow { MessageException(ResultCode.COFFEE_NOT_FOUND) }
    }

    fun saveCoffee(request: CoffeeCreateRequest) : CoffeeResponse {
        return coffeeRepository.save(request.toEntity()).let { CoffeeResponse.of(it) }
    }

    @Transactional
    fun updateCoffee(request: CoffeeUpdateRequest) : CoffeeResponse {
        val coffee = coffeeRepository.findById(request.id)
            .orElseThrow { MessageException(ResultCode.COFFEE_NOT_FOUND) }

        coffee.updateName(request.name)
        return coffee.let { CoffeeResponse.of(it) }
    }

    fun deleteCoffee(id: Long) {
        val coffee = coffeeRepository.findById(id)
            .orElseThrow { MessageException(ResultCode.COFFEE_NOT_FOUND) }

        coffeeRepository.delete(coffee)
    }

}