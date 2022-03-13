package org.help.ukraine.hosting.domain.model

class Host(
    age: Int,
    gender: Gender,
    constraints: Constraints<Person>,
    languages: Set<Language>
): Person(ageRange = AgeRange.withAge(age), gender = gender, constraints = constraints, languages = languages)