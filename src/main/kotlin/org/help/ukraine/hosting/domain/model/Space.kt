package org.help.ukraine.hosting.domain.model

sealed class Space(
    open val beds: Set<Bed>,
    open val constraints: Constraints<*>
) {
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
                when (pet.isAccepted(constraints)) {
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

class Room(
    override val beds: Set<Bed>,
    constraints: Constraints<Room>
): Space(beds = beds,constraints = constraints)

class Apartment(
    val rooms: Rooms,
    constraints: Constraints<Space>
): Space(beds = rooms.getBedsFromAllRooms(), constraints = constraints )

class House(
    val rooms: Rooms,
    constraints: Constraints<Space>
): Space(beds = rooms.getBedsFromAllRooms(), constraints = constraints )

class Rooms(rooms: Set<Room>): Set<Room> by rooms {
    fun getBedsFromAllRooms() = this.flatMap { it.beds }.toSet()
}