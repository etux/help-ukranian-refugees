package org.help.ukraine.hosting.domain.model

import java.time.Instant
import java.util.*
import javax.persistence.Entity

@Entity
class Availability(
    val from: Instant,
    val to: Instant
) : AbstractJpaPersistable<UUID>() {
    fun coverage(timeRange: TimeRange): Int {
        return 100
    }
}