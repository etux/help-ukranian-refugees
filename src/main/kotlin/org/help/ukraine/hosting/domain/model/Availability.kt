package org.help.ukraine.hosting.domain.model

import java.time.Instant
import java.util.*
import javax.persistence.Embeddable
import javax.persistence.Entity

@Embeddable
class Availability(
    val from: Instant,
    val to: Instant
) {
    fun coverage(availability: Availability): Int {
        return 100
    }
}