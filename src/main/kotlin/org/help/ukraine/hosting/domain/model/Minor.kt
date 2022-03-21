package org.help.ukraine.hosting.domain.model

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("minor")
class Minor(
    @field:OneToMany val responsibles: Set<Adult>,
    override val ageRange: AgeRange =  AgeRange.UNDEFINED,
    override val languages: Set<Language> = mutableSetOf()
) : Person<Minor> (
    ageRange = ageRange,
    languages = languages
) {
    override fun toGuest(request: Request): Guest<Minor> {
        return ChildGuest(this, request)
    }

    override fun toHost(): Host<Minor>{
        throw RuntimeException("A child cannot host")
    }
}

