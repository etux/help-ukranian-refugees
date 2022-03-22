package org.help.ukraine.hosting.domain.model

import org.help.ukraine.hosting.domain.jpa.converters.AgeRangeConverter
import java.util.*
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
abstract class Person<P: Person<P>>(
    @field:Convert(converter = AgeRangeConverter::class) val ageRange: AgeRange,
    @field:ElementCollection(targetClass = Language::class) val languages: Set<Language> = UNDEFINED_LANGUAGES,
) : AbstractJpaPersistable<UUID>()
{
    abstract fun toGuest(request: Request): Guest<P>

    abstract fun toHost(): Host<P>

    companion object {
        const val UNDEFINED_AGE = -1
        const val UNDEFINED = "undefined"
        val UNDEFINED_LANGUAGES = emptySet<Language>()
    }
}

enum class Gender {
    FEMALE, MALE, NEUTRAL, UNDEFINED
}