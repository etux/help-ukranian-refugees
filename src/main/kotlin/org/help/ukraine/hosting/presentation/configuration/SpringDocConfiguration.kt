package org.help.ukraine.hosting.presentation.configuration

import org.springdoc.core.GroupedOpenApi
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("local")
class SpringDocConfiguration {

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi
            .builder()
            .group("public")
            .pathsToMatch("/api/**")
            .packagesToScan("org.help.ukraine.hosting.presentation.controllers")
            .build()
    }

    @Bean
    fun adminApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("admin")
            .pathsToMatch("/admin-api/**")
            .build()
    }

}