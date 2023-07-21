package com.jeayeob.coffeelog.repository

import com.jeayeob.coffeelog.entity.Coffee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CoffeeRepository : JpaRepository<Coffee, Long> {
    fun findByName(name: String): Optional<Coffee>

}