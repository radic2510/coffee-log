package com.jeayeob.coffeelog.service

import com.jeayeob.coffeelog.constant.Processing
import com.jeayeob.coffeelog.constant.ResultCode
import com.jeayeob.coffeelog.dto.CoffeeCreateRequest
import com.jeayeob.coffeelog.dto.CoffeeUpdateRequest
import com.jeayeob.coffeelog.entity.Coffee
import com.jeayeob.coffeelog.exception.MessageException
import com.jeayeob.coffeelog.repository.CoffeeRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CoffeeServiceTest @Autowired constructor(
    private val coffeeRepository: CoffeeRepository,
    private val coffeeService: CoffeeService,
) {

    @BeforeEach
    fun init() {
        coffeeRepository.save(Coffee("테스트원두1"))
        coffeeRepository.save(Coffee("테스트원두2"))
    }

    @AfterEach
    fun clean() {
        coffeeRepository.deleteAll()
    }

    @Test
    @DisplayName("모든 커피 조회가 정상 작동한다.")
    fun getAllCoffee() {
        // given
        // when
        val coffees = coffeeService.getAllCoffee()

        // then
        assertEquals(2, coffees.size)
    }

    @Test
    @DisplayName("커피 이름으로 커피 조회가 정상 동작한다.")
    fun getCoffeeByName() {
        // given
        val coffeeName = "테스트원두1"

        // when
        val coffee = coffeeService.getCoffeeByName(coffeeName)

        // then
        assertEquals("테스트원두1", coffee.name)
    }

    @Test
    @DisplayName("커피 이름으로 커피를 조회할 때, 커피가 없으면 에러를 발생시킨다.")
    fun getCoffeeByNameNotFound() {
        // given
        val coffeeName = "테스트원두3"

        // when
        val assertThrows = assertThrows(MessageException::class.java) {
            coffeeService.getCoffeeByName(coffeeName)
        }

        // then
        assertEquals(ResultCode.COFFEE_NOT_FOUND.message, assertThrows.message)
    }

    @Test
    @DisplayName("커피 저장이 정상 동작한다.")
    fun saveCoffee() {
        // given
        val request = CoffeeCreateRequest(name = "테스트원두3", processing = Processing.NATURAL)

        // when
        val savedCoffee = coffeeService.saveCoffee(request)

        // then
        val newCoffee = coffeeRepository.findByName(savedCoffee.name).orElseThrow()
        assertEquals(newCoffee.name, savedCoffee.name)
    }

    @Test
    @DisplayName("커피 업데이트가 정상 동작한다.")
    fun updateCoffee() {
        // given
        val savedCoffee = coffeeRepository.save(Coffee("임시이름"))
        val newName = "정상이름"
        val request = CoffeeUpdateRequest(savedCoffee.id, newName)


        // when
        val updatedCoffee = coffeeService.updateCoffee(request)

        // then
        assertEquals(newName, updatedCoffee.name)
    }

    @Test
    @DisplayName("커피 삭제가 정상 동작한다.")
    fun deleteCoffee() {
        // given
        val savedCoffee = coffeeRepository.save(Coffee("임시이름"))
        val originalListSize = coffeeRepository.findAll().size

        // when
        coffeeService.deleteCoffee(savedCoffee.id)

        // then
        assertThat(originalListSize - 1).isEqualTo(coffeeRepository.findAll().size)

        val assertThrows = assertThrows(MessageException::class.java) {
            coffeeService.getCoffeeByName(savedCoffee.name)
        }
        assertEquals(ResultCode.COFFEE_NOT_FOUND.message, assertThrows.message)
    }


}