package org.help.ukraine.hosting.presentation.mappers

import org.help.ukraine.hosting.presentation.dtos.AvailabilityDto
import org.help.ukraine.hosting.presentation.dtos.HostingRequestDto
import org.help.ukraine.hosting.presentation.dtos.PersonDto
import org.help.ukraine.hosting.presentation.dtos.PetDto
import org.help.ukraine.hosting.domain.model.*
import org.help.ukraine.hosting.services.ServiceType
import org.springframework.stereotype.Component

@Component
class GuestServiceMapper {
    fun map(dto: HostingRequestDto): Request {
        return Request(
            serviceType = ServiceType.HOSTING,
            pets = dto.pets.map { map(it) }.toMutableSet(),
            availability = map(dto.availability)
        )
    }

    fun map(model: Request) = HostingRequestDto(
        availability = map(model.availability),
        pets = model.pets.map { map(it) }.toSet(),
        people = model.guests.map { map(it) }.toSet()
    )

    fun map(guest: Guest<*>) = PersonDto(
        ageRange = guest.person.ageRange.toString(),
        languages = guest.person.languages.map { it.toString() }.toSet()
    )

    fun map(availability: Availability) = AvailabilityDto(
        from = availability.from,
        to = availability.to,
    )

    fun map(pet: Pet) = PetDto(
        type = pet.type.toString(),
        gender = pet.gender.toString(),
        size = pet.size.toString()
    )

    fun map(dto: AvailabilityDto) = Availability(
        from = dto.from,
        to = dto.to
    )

    fun map(dto: PetDto) = Pet(
            type = Pet.Type.valueOf(dto.type),
            gender = Gender.valueOf(dto.gender),
            size = Pet.Size.valueOf(dto.size)
    )
//
//    fun map(dto: PersonDto) = Person(
//        ageRange = map(dto.ageRange),
//        languages = map(dto.languages)
//    )
}
