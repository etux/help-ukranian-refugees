package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.*

@Entity
class Space(
    @field:OneToMany val beds: Set<Bed>,
) : AbstractJpaPersistable<UUID>() {

    fun cover(people: Set<Guest<*>>): BedCoverage {
        return BedCoverage(this, people).calculate()
    }

    fun cover(pets: Set<Pet>): PetCoverage {
        return PetCoverage(this, pets).calculate()
    }

    fun allows(it: Pet): Boolean {
        TODO("Not yet implemented")
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