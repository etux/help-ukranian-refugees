package org.help.ukraine.hosting.domain.model

import org.help.ukraine.hosting.services.ServiceType
import java.util.*
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
sealed class Offering(
    @field:OneToOne open val availability: Availability,
    @field:Enumerated(value = EnumType.STRING) private val serviceType: ServiceType
) : AbstractJpaPersistable<UUID>()

@Entity
class Driving(
    @field:OneToOne val vehicle: Vehicle,
    @OneToOne
    override val availability: Availability,
): Offering(availability = availability, serviceType = ServiceType.DRIVING)

@Entity
class Hosting(
    @field:OneToOne val space: Space,
    @OneToOne
    override val availability: Availability
): Offering(availability = availability, serviceType = ServiceType.HOSTING)