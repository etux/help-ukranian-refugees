package org.help.ukraine.hosting.domain.model

import javax.persistence.Entity

@Entity
class Host(
    age: Int,
    gender: Gender,
    constraints: Constraints,
    languages: Set<Language>
): Person(ageRange = AgeRange.withAge(age), gender = gender, constraints = constraints, languages = languages)