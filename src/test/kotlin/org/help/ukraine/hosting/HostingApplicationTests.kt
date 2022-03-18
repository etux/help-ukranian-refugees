package org.help.ukraine.hosting

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(classes = [HostingApplication::class])
@Testcontainers
class HostingApplicationTests {

	@Test
	fun contextLoads() {
	}

	companion object {
		@JvmStatic
		@Container
		val postgresContainer = PostgreSQLContainer("postgres")

		@JvmStatic
		@DynamicPropertySource
		fun register(register: DynamicPropertyRegistry) {
			register.add("spring.datasource.url", postgresContainer::getJdbcUrl)
			register.add("spring.datasource.username", postgresContainer::getUsername)
			register.add("spring.datasource.password", postgresContainer::getPassword)
			register.add("spring.datasource.driver-class-name", postgresContainer::getDriverClassName)
		}
	}

}
