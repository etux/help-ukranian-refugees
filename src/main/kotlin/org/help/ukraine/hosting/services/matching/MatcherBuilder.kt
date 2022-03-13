package org.help.ukraine.hosting.services.matching

import org.help.ukraine.hosting.domain.model.*
import org.help.ukraine.hosting.services.driving.DrivingService
import org.help.ukraine.hosting.services.driving.DrivingServiceMatcher
import org.help.ukraine.hosting.services.hosting.HostService
import org.help.ukraine.hosting.services.hosting.HostServiceMatcher

abstract class MatcherBuilder<T: MatchProducer>(private val request: Request){
    abstract fun build(): Matcher
}

class HostServiceMatcherBuilder(
    private val request: Request,
    private val hostService: HostService
): MatcherBuilder<HostService>(request = request) {
    override fun build(): Matcher {
        return HostServiceMatcher(request = request, hostService = hostService)
    }
}

class DrivingServiceMatcherBuilder(
    private val request: Request,
    private val drivingService: DrivingService
): MatcherBuilder<DrivingService>(request = request) {

    override fun build(): Matcher {
        return DrivingServiceMatcher()
    }
}