package org.help.ukraine.hosting.domain.model

import java.util.UUID
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
abstract class Guest<T: Person<T>> (
    @field:ManyToOne val person: T,
    @field:ManyToOne val request: Request
): AbstractJpaPersistable<UUID> () {

    fun isCoveredBy(bed: Bed): Boolean {
        return bed.covers(this)
    }
}

@Entity
@DiscriminatorValue("adult")
class AdultGuest(
    @field:ManyToOne(targetEntity = Adult::class)
    override val person: Adult,
    override val request: Request
) : Guest<Adult>(person = person, request = request)

@Entity
@DiscriminatorValue("minor")
class ChildGuest(
    @field:ManyToOne(targetEntity = Minor::class)
    override val person: Minor,
    override val request: Request
) : Guest<Minor>(person = person, request = request)