package org.help.ukraine.hosting.services.matching

import org.help.ukraine.hosting.domain.model.Match
import org.help.ukraine.hosting.domain.model.Offer
import org.help.ukraine.hosting.domain.model.ProspectMatch
import org.help.ukraine.hosting.domain.model.Request

class MatchBuilder {

    private lateinit var request: Request
    private lateinit var offer: Offer<*>
    private lateinit var availabilityScore: Score
    private lateinit var spaceScore: Score

    fun request(request: Request): MatchBuilder {
        this.request = request
        return this
    }

    fun offering(offering: Offer<*>): MatchBuilder {
        this.offer = offering
        return this;
    }

    fun availabilityScore(score: Score): MatchBuilder {
        this.availabilityScore = score
        return this
    }

    fun spaceScore(score: Score): MatchBuilder {
        this.spaceScore = score
        return this
    }

    fun build(): Match {
        return ProspectMatch(request = request, offer = offer)
    }

}
