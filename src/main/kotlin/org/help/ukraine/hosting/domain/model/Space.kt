package org.help.ukraine.hosting.domain.model

import org.help.ukraine.hosting.domain.jpa.converters.ConstraintsConverter
import java.util.*
import javax.persistence.*

@Entity
class Space(
    @field:OneToMany val beds: Set<Bed>,
    @field:Convert(converter = ConstraintsConverter::class)
    @field:OneToMany val constraints: Constraints
) : AbstractJpaPersistable<UUID>(), Constrained {

    fun coverage(people: People): Int {
        val remainingBeds = beds.toMutableSet()
        val remainingPeople = people.toMutableSet()

        val peopleLeftWithoutBed = people
            .fold(remainingPeople) {
                acc, person ->
                    val coveringBed = person.isCoveredBy((remainingBeds))
                    if (coveringBed != null) {
                        acc.remove(person)
                        if(coveringBed.isFull()) {
                            remainingBeds.remove(coveringBed)
                        }
                    }
                    acc
            }

        return 100 - ((peopleLeftWithoutBed.size / people.size) * 100)
    }

    fun coverage(pets: Set<Pet>): Int {
        val remainingPets = pets.toMutableSet()

        val petsNotMatchingConstraints = pets.fold(remainingPets) {
            acc, pet ->
                when (pet.check(constraints)) {
                    is PositiveConstraintResult -> {
                        acc.remove(pet)
                        acc
                    }
                    else -> remainingPets
                }
        }

        return 100 - ((petsNotMatchingConstraints.size / pets.size) * 100)
    }
}

//@Entity
//class Room(
//    override val beds: Set<Bed>,
//    override val constraints: Constraints<Space>
//): Space(beds = beds,constraints = constraints)

//@Entity
//class Apartment(
//    val rooms: Rooms,
//    @field:Convert(converter = ConstraintsConverter::class) override val constraints: Constraints<Space>
//): Space(beds = rooms.getBedsFromAllRooms(), constraints = constraints )
//
//@Entity
//class House(
//    val rooms: Rooms,
//    @field:Convert(converter = ConstraintsConverter::class) override val constraints: Constraints<Space>
//): Space(beds = rooms.getBedsFromAllRooms(), constraints = constraints )
//
//class Rooms(rooms: Set<Room>): Set<Room> by rooms {
//    fun getBedsFromAllRooms() = this.flatMap { it.beds }.toSet()
//}