package org.help.ukraine.hosting.domain.model


sealed class Person(
    open val ageRange: AgeRange,
    open val gender: Gender = Gender.UNDEFINED,
    open val languages: Set<Language> = UNDEFINED_LANGUAGES,
    open val constraints: Constraints<Person> = UNDEFINED_CONSTRAINTS
)
{
    fun isCoveredBy(remainingBeds: Set<Bed>): Bed? {
        return remainingBeds.firstOrNull { it.covers(this) } ?.assign(this)
    }

    companion object {
        const val UNDEFINED_AGE = -1
        const val UNDEFINED = "undefined"
        val UNDEFINED_LANGUAGES = emptySet<Language>()
        val UNDEFINED_CONSTRAINTS = Constraints(emptySet<Constraint<Person>>())

    }
}

enum class Gender {
    FEMALE, MALE, NEUTRAL, UNDEFINED
}

class People(people: Set<Person>): Set<Person> by people