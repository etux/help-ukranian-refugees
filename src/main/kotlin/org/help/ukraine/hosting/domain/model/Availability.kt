package org.help.ukraine.hosting.domain.model

import java.time.Instant

class Availability(
    val from: Instant,
    val to: Instant
) {
    fun coverage(timeRange: TimeRange): Int {
        return 100
    }
}