package org.help.ukraine.hosting.domain.model

import java.time.Instant
import java.util.*

sealed class Match(
    open val offer: Offer<*>,
    open val request: Request
)

class ProspectMatch(
    override val offer: Offer<*>,
    override val request: Request
): Match(offer = offer, request = request)

class ProposedMatch(
    override val offer: Offer<*>,
    override val request: Request,
): Match(offer = offer, request = request)

class ConfirmedMatch(
    override val offer: Offer<*>,
    override val request: Request,
    val matchedBy: UUID,
    val matchedOn: Instant
): Match(offer = offer, request = request)