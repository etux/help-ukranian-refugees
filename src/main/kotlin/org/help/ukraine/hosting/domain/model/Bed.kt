package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Bed(
    @field:Id private val id: UUID = UUID.randomUUID(),
    private val type: BedTypes,
    @field:OneToMany private val assignedPeople: Set<Person>
) {

    fun isFull(): Boolean {
        return assignedPeople.size >= type.places
    }

    fun covers(person: Person): Boolean {
        return assignedPeople.contains(person) || (assignedPeople.size + 1 <= type.places && type.ages.intersect(person.ageRange).size > 0)
    }

    fun assign(person: Person) = Bed (
        type = this.type,
        assignedPeople =  assignedPeople + person
    )
}

enum class BedTypes(
    val places: Int,
    val ages: IntRange
) {
    Crib(1, IntRange(0, 4)),
    SingleBed(1, IntRange(5, 100)),
    DoubleBed(2, IntRange(5, 100)),
    SingleCouchBed(1, IntRange(5, 100)),
    DoubleCouchBed(2, IntRange(5, 100))
}