package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type")
sealed class Offering(
    open val availability: Availability
) : AbstractJpaPersistable<UUID>()

@Entity
@DiscriminatorValue("driving")
class Driving(
    @field:OneToOne val vehicle: Vehicle,
    override val availability: Availability,
): Offering(availability = availability)

@Entity
@DiscriminatorValue("hosting")
class Hosting(
    @field:OneToOne val space: Space,
    override val availability: Availability
): Offering(availability = availability)