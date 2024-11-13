package com.jeayeob.coffeelog.config

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource


abstract class TestConfig {
    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun setDatasourceProperties(registry: DynamicPropertyRegistry) {
            LocalStackTestContainer.setProperties(registry, LocalStackTestContainer.localstackContainer)
        }
    }
}