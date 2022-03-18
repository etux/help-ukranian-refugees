package org.help.ukraine.hosting.domain.model

import org.help.ukraine.hosting.domain.jpa.converters.AgeRangeConverter
import org.help.ukraine.hosting.domain.jpa.converters.ConstraintConverter
import java.util.*
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Person(
    @field:Convert(converter = AgeRangeConverter::class) open val ageRange: AgeRange,
    @field:Enumerated(EnumType.STRING) private val gender: Gender = Gender.UNDEFINED,
    @field:ElementCollection(targetClass = Language::class) open val languages: Set<Language> = UNDEFINED_LANGUAGES,
    @field:Convert(converter = ConstraintConverter::class)
    @field:OneToMany(targetEntity = Person::class)
    open val constraints: Constraints = UNDEFINED_CONSTRAINTS
) : AbstractJpaPersistable<UUID>(), Constrained
{
    fun isCoveredBy(remainingBeds: Set<Bed>): Bed? {
        return remainingBeds.firstOrNull { it.covers(this) } ?.assign(this)
    }

    companion object {
        const val UNDEFINED_AGE = -1
        const val UNDEFINED = "undefined"
        val UNDEFINED_LANGUAGES = emptySet<Language>()
        val UNDEFINED_CONSTRAINTS = Constraints(emptySet())
    }
}

enum class Gender {
    FEMALE, MALE, NEUTRAL, UNDEFINED
}

class People(people: Set<Person>): Set<Person> by people