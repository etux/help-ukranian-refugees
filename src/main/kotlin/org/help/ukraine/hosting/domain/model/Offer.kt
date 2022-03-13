package org.help.ukraine.hosting.domain.model

sealed class Offer<T: Offering>(
    val provider: Person,
    val offering: T
)

class HostingOffer(
    provider: Person,
    offering: Hosting
): Offer<Hosting>(provider = provider, offering = offering)

class DrivingOffer(
    provider: Person,
    offering: Driving
): Offer<Driving>(provider = provider, offering = offering)