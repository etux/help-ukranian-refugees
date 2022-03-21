package org.help.ukraine.hosting.test

import org.help.ukraine.hosting.domain.model.*
import org.help.ukraine.hosting.services.ServiceType
import java.time.Instant
import java.time.temporal.ChronoUnit

object TestFactory {

    fun createRequest(
        serviceType: ServiceType = ServiceType.HOSTING,
        timeRange: Availability = Availability(
            from = Instant.now(),
            to = Instant.now().plus(1, ChronoUnit.DAYS)
        )
    ) = Request(
        serviceType = serviceType,
        availability = timeRange
    )

    fun createAdult() = Adult(
        email = "john.dow@example.com",
        firstName = "John",
        lastName = "Doe",
        age = 18
    )

    fun createChild() = Minor(
        responsibles = setOf(createAdult()),
        ageRange = AgeRange.BabyAge
    )
}
