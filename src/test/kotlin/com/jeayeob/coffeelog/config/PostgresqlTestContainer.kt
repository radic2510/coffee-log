package com.jeayeob.coffeelog.config

import jakarta.annotation.PreDestroy
import org.springframework.stereotype.Component
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container

@Component
class PostgresqlTestContainer {

    // 사실 테스트가 모두 진행된 후에는 TestContainer를 모니터링하고 있던
    // Ryuk Container가 알아서 container를 종료시킴
    // 실제로 테스트를 실행시키고 docker ps를 해보시면 2개의 컨테이너가 실행되는 것을 확인
    @PreDestroy
    fun stop() {
        POSTGRES_CONTAINER.stop()
    }
    companion object {
        @Container
        @JvmStatic
        val POSTGRES_CONTAINER: PostgreSQLContainer<*> = PostgreSQLContainer<Nothing>("postgres:alpine")
            .apply { withDatabaseName(DATABASE_NAME) }
            .apply { withUsername(USERNAME) }
            .apply { withPassword(PASSWORD) }
            .apply { withInitScript("db/init.sql") }
            .apply { start() }


        const val DATABASE_NAME: String = "test_database"
        const val USERNAME: String = "root"
        const val PASSWORD: String = "password"
    }
}