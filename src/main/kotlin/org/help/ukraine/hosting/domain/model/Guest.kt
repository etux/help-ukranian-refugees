package org.help.ukraine.hosting.domain.model

import javax.persistence.Entity

@Entity
class Guest(
    age: Int,
    gender: Gender = Gender.UNDEFINED,
    constraints: Constraints = UNDEFINED_CONSTRAINTS,
    languages: Set<Language> = UNDEFINED_LANGUAGES
): Person(ageRange = AgeRange.withAge(age), gender = gender, constraints = constraints, languages = languages)