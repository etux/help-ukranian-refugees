package org.help.ukraine.hosting.domain.model

class Guest(
    age: Int,
    gender: Gender = Gender.UNDEFINED,
    constraints: Constraints<Person> = UNDEFINED_CONSTRAINTS,
    languages: Set<Language> = UNDEFINED_LANGUAGES
): Person(ageRange = AgeRange.withAge(age), gender = gender, constraints = constraints, languages = languages)