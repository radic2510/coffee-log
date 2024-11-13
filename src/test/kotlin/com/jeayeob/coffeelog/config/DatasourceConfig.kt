package com.jeayeob.coffeelog.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import javax.sql.DataSource

@Configuration
class DatasourceConfig {

    @Bean
    @DependsOn("postgresqlTestContainer")
    fun dataSource(): DataSource =
        DataSourceBuilder.create()
            .url(
                "jdbc:postgresql://localhost:" +
                        "${PostgresqlTestContainer.POSTGRES_CONTAINER.getMappedPort(5432)}" +
                        "/${PostgresqlTestContainer.DATABASE_NAME}"
            )
            .driverClassName("org.postgresql.Driver")
            .username(PostgresqlTestContainer.USERNAME)
            .password(PostgresqlTestContainer.PASSWORD)
            .build()
}
