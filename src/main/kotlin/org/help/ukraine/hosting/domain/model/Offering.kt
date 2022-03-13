package org.help.ukraine.hosting.domain.model

import org.help.ukraine.hosting.services.ServiceType

sealed class Offering(
    open val availability: Availability,
    private val serviceType: ServiceType
)

class Driving(
    val vehicle: Vehicle,
    override val availability: Availability,
): Offering(availability = availability, serviceType = ServiceType.DRIVING)

class Hosting(
    val space: Space,
    override val availability: Availability
): Offering(availability = availability, serviceType = ServiceType.HOSTING)