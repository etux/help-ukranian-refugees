package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.*

@Entity
@DiscriminatorColumn(name = "type")
abstract class Offer<T: Offering>(
    @field:ManyToOne val provider: Adult,
    @field:OneToOne val offering: T
) : AbstractJpaPersistable<UUID>()

@Entity
@DiscriminatorValue("hosting")
class HostingOffer(
    @field:ManyToOne override val provider: Adult,
    @field:OneToOne override val offering: Hosting
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