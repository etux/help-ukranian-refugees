package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.*

@Entity
class Pet (
    @field:Enumerated(EnumType.STRING) val type: Type = Type.UNDEFINED,
    @field:Enumerated(EnumType.STRING) val gender: Gender = Gender.UNDEFINED,
    @field:Enumerated(EnumType.STRING) val size: Size = Size.UNDEFINED
): AbstractJpaPersistable<UUID>(){

    enum class Type {
        CAT, DOG, UNDEFINED
    }

    enum class Size {
        SMALL,
        MEDIUM,
        BIG,
        UNDEFINED
    }


}