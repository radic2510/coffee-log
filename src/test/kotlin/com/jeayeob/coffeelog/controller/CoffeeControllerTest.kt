package com.jeayeob.coffeelog.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jeayeob.coffeelog.dto.CoffeeCreateRequest
import com.jeayeob.coffeelog.dto.CoffeeUpdateRequest
import com.jeayeob.coffeelog.entity.Coffee
import com.jeayeob.coffeelog.repository.CoffeeRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class CoffeeControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val coffeeRepository: CoffeeRepository,
) {

    @AfterEach
    fun clean() {
        coffeeRepository.deleteAll()
    }

    @Test
    fun getCoffeeByName() {
        // given
        val coffeeName = "테스트원두1"
        val uri = "/coffee/$coffeeName"

        coffeeRepository.save(Coffee(name = coffeeName))

        // when & then
        mockMvc.perform(get(uri))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(coffeeName))
            .andDo(print())
    }

    @Test
    fun saveCoffee() {
        // given
        val uri = "/coffee"
        val request = CoffeeCreateRequest(name = "테스트원두1")
        val json = jacksonObjectMapper().writeValueAsString(request)

        // when
        mockMvc.perform(
            post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(request.name))
            .andDo(print())

        // then
        val coffee = coffeeRepository.findByName(request.name)
            .orElseThrow { throw Exception() }
        assertEquals(request.name, coffee.name)
    }

    @Test
    fun updateCoffee() {
        // given
        val targetCoffee = coffeeRepository.save(Coffee(name = "테스트원두1"))

        val uri = "/coffee"
        val request = CoffeeUpdateRequest(id = targetCoffee.id, name = "테스트원두2")
        val json = jacksonObjectMapper().writeValueAsString(request)

        // when
        mockMvc.perform(
            put(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(request.name))
            .andDo(print())

        // then
        val coffee = coffeeRepository.findByName(request.name)
            .orElseThrow { throw Exception() }

        assertEquals(request.name, coffee.name)
    }

    @Test
    fun deleteCoffee() {
        // given
        coffeeRepository.save(Coffee(name = "테스트원두1"))

        val uri = "/coffee"
        val request = CoffeeCreateRequest(name = "테스트원두2")
        val json = jacksonObjectMapper().writeValueAsString(request)

        // when
        mockMvc.perform(get(uri))
            .andExpect(status().isOk)
            .andDo(print())

        // then
        assertEquals(true, coffeeRepository.findByName(request.name).isEmpty)

        //.orElse(null)
    }
}