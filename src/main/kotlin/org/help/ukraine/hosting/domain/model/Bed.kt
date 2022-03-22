package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Bed(
    @field:Id private val id: UUID = UUID.randomUUID(),
    private val type: BedTypes,
    @field:Transient private val assignedPeople: Set<Guest<*>>
) {

    fun isFull(): Boolean {
        return assignedPeople.size >= type.places
    }

    fun covers(guest: Guest<*>): Boolean {
        return assignedPeople.contains(guest) ||
                (assignedPeople.size + 1 <= type.places && type.ages.intersect(guest.person.ageRange).isNotEmpty())
    }

    fun assign(person: Guest<*>) = Bed (
        type = this.type,
        assignedPeople =  assignedPeople + person
    )
}

enum class BedTypes(
    val places: Int,
    val ages: IntRange
) {
    Crib(1, IntRange(0, 4)),
    Single(1, IntRange(5, 100)),
    Double(2, IntRange(5, 100)),
    SingleCouch(1, IntRange(5, 100)),
    DoubleCouch(2, IntRange(5, 100))
}