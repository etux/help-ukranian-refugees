package org.help.ukraine.hosting.domain.model

import org.help.ukraine.hosting.services.ServiceType

class Request(
    val serviceType: ServiceType,
    val people: People,
    val pets: Set<Pet>,
    val timeRange: TimeRange,
    val constraints: Constraints<*>
)