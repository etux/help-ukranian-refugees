package org.help.ukraine.hosting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@SpringBootApplication
class HostingApplication {
	fun main(args: Array<String>) {
		runApplication<HostingApplication>(*args)
	}
}
