package org.help.ukraine.hosting.domain.model

import javax.persistence.Entity

@Entity
class Child(
    ageRange: AgeRange,
    gender: Gender,
    constraints: Constraints,
    languages: Set<Language>
) : Person (
    ageRange = ageRange,
    gender = gender,
    constraints = constraints,
    languages = languages
)

