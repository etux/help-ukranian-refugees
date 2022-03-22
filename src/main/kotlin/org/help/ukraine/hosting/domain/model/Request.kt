package org.help.ukraine.hosting.domain.model

import org.help.ukraine.hosting.services.ServiceType
import java.util.*
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Request(
    val serviceType: ServiceType,
    @field:OneToMany val guests: MutableSet<Guest<*>> = mutableSetOf(),
    @field:OneToMany val pets: MutableSet<Pet> = mutableSetOf(),
    @field:Embedded val availability: Availability,
) : AbstractJpaPersistable<UUID>()