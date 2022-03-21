package org.help.ukraine.hosting.domain.model

import javax.persistence.*

@Entity
@DiscriminatorValue("adult")
class Adult(
    @field:Column(name = "email") val email: String,
    @field:Column(name = "first_name") val firstName: String = UNDEFINED,
    @field:Column(name = "middle_name") val middleName: String = UNDEFINED,
    @field:Column(name = "last_name") val lastName: String = UNDEFINED,
    @field:Column(name = "telephone_number") val telephoneNumber: String = UNDEFINED,
    @field:Column(name = "age") val age: Int = UNDEFINED_AGE,
    @field:Column(name = "gender") @field:Enumerated(EnumType.STRING) val gender: Gender = Gender.UNDEFINED
): Person<Adult>(ageRange = AgeRange.AdultAge) {

    override fun toGuest(request: Request): Guest<Adult> {
        return AdultGuest(this, request).also { request.guests.add(it) }
    }

    override fun toHost(): Host<Adult> {
        TODO("Not yet implemented")
    }
}