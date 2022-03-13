package org.help.ukraine.hosting.services.hosting

import org.help.ukraine.hosting.domain.model.*
import org.help.ukraine.hosting.services.matching.MatchProducer
import org.help.ukraine.hosting.services.matching.Matcher
import java.time.Instant

class HostService: MatchProducer {

    fun register(host: Host): Host {
        return host
    }

    fun register(offer: HostingOffer): HostingOffer {
        return offer
    }

    fun unregister(offer: HostingOffer): Boolean {
        return true
    }

    fun findOfferingsFromTo(from: Instant, to: Instant) {
        TODO("Not yet implemented")
    }

    override fun match(matcher: Matcher): Set<Match> {
        TODO("Not yet implemented")
    }

    fun findOfferingsFor(timeRange: TimeRange): Set<HostingOffer> {
        TODO("Not yet implemented")
    }

    fun findOfferingsFor(people: People): Set<HostingOffer> {
        TODO("Not yet implemented")
    }

    fun findOfferingsFor(pets: Set<Pet>): Set<HostingOffer> {
        TODO("Not yet implemented")
    }
}