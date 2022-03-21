package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.OneToOne

@MappedSuperclass
sealed class Offer<T: Offering>(
    @field:Id private val id: UUID = UUID.randomUUID(),
    @field:OneToOne open val provider: Adult,
    @field:OneToOne open val offering: T
)

@Entity
class HostingOffer(
    override val provider: Adult,
    override val offering: Hosting
): Offer<Hosting>(
    provider = provider,
    offering = offering
)

//
//@Entity
//class DrivingOffer(
//    override val provider: Person,
//    override val offering: Driving
//): Offer<Driving>(provider = provider, offering = offering)