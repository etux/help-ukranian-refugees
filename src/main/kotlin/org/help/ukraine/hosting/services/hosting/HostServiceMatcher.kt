package org.help.ukraine.hosting.services.hosting

import org.help.ukraine.hosting.domain.model.*
import org.help.ukraine.hosting.services.matching.Score
import org.help.ukraine.hosting.services.matching.MatchBuilder
import org.help.ukraine.hosting.services.matching.Matcher


class HostServiceMatcher(
    private val request: Request,
    private val hostService: HostService
) : Matcher {

    override fun execute(): Set<Match> {
        return Execution(hostService, request)
            .matchTimeRange()
            .matchPeople()
            .matchPets()
            .score()
    }

    class Execution(private val hostService: HostService, private val request: Request) {
        private val offers: MutableSet<HostingOffer> = mutableSetOf()

        fun matchTimeRange(): Execution {
            offers += hostService.findOfferingsFor(request.timeRange)
            return this
        }

        fun matchPeople(): Execution {
            offers += hostService.findOfferingsFor(request.people)
            return this
        }

        fun matchPets(): Execution {
            offers += hostService.findOfferingsFor(request.pets)
            return this
        }

        fun score(): Set<Match> {
            return offers.map { score(it) }.toSet()
        }

        fun score(offer: HostingOffer): Match {
            return MatchBuilder()
                .request(request)
                .offering(offer)
                .availabilityScore(score(offer.offering.availability))
                .spaceScore(score(offer.offering.space))
                .build()
        }

        fun score(availability: Availability): Score {
            return Score(
                availability.coverage(request.timeRange)
            )
        }

        fun score(space: Space): Score {
            return Score(
                space.coverage(request.people) + space.coverage(request.pets)
            )
        }
    }

}
