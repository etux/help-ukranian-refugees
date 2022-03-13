package org.help.ukraine.hosting.domain.model

import java.time.Instant

sealed class TimeRange {
    abstract fun coverage(availability: Availability): Coverage
}

class AbsoluteTimeRange(
    private val from: Instant,
    private val to: Instant
): TimeRange() {

    override fun coverage(availability: Availability): Coverage {
        return Coverage(CoverageType.CLOSED)
    }
}

class RelativeTimeRange: TimeRange() {
    override fun coverage(availability: Availability): Coverage {
        return Coverage(CoverageType.OPEN)
    }
}