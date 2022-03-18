package org.help.ukraine.hosting.domain.model

import javax.persistence.Entity

@Entity
class Adult(
    val firstName: String = UNDEFINED,
    val middleName: String = UNDEFINED,
    val lastName: String = UNDEFINED,
    val telephoneNumber: String = UNDEFINED,
    val age: Int = UNDEFINED_AGE
): Person(ageRange = AgeRange.AdultAge)