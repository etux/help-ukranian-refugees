package org.help.ukraine.hosting.domain.model

import org.help.ukraine.hosting.domain.jpa.converters.ConstraintsConverter
import org.help.ukraine.hosting.services.ServiceType
import java.util.*
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Request(
    val serviceType: ServiceType,
    val people: People,
    val pets: Set<Pet>,
    val timeRange: TimeRange,
    @OneToMany
    @Convert(
        converter = ConstraintsConverter::class
    )
    val constraints: Constraints
) : AbstractJpaPersistable<UUID>()