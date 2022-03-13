package org.help.ukraine.hosting.services.matching

import org.help.ukraine.hosting.domain.model.*
import org.help.ukraine.hosting.services.driving.DrivingService
import org.help.ukraine.hosting.services.hosting.HostService
import org.help.ukraine.hosting.services.ServiceType
import java.time.Clock

class MatchingService(
    private val hostService: HostService,
    private val drivingService: DrivingService,
    private val applicationClock: Clock
) {

    fun findMatches(request: Request): Set<Match> {
        return when (request.serviceType) {
            ServiceType.HOSTING -> HostServiceMatcherBuilder(request, hostService)
            ServiceType.DRIVING -> DrivingServiceMatcherBuilder(request, drivingService)
        }
        .build()
        .execute()
    }

    fun unmatch(match: Match): Boolean {
        return true
    }
}