package org.help.ukraine.hosting.presentation.dtos

data class HostingRequestDto(
    val availability: AvailabilityDto,
    val pets: Set<PetDto>,
    val people: Set<PersonDto>
)
