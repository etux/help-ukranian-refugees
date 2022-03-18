package org.help.ukraine.hosting.domain.model

import org.help.ukraine.hosting.domain.jpa.converters.ConstraintsConverter
import java.util.*
import javax.persistence.*

@Entity
class Pet (
    @field:Enumerated(EnumType.STRING) private val gender: Gender,
    @field:Enumerated(EnumType.STRING) private val size: PetSize,
    @field:Convert(converter = ConstraintsConverter::class)
    @field:ManyToOne val constraints: Constraints,
): AbstractJpaPersistable<UUID>(), Constrained {

    override fun check(check: Constraints): ConstraintResult {
        val result = check
            .fold(CombinedConstraintResult(this)) {
                acc, constraint -> acc.merge(this.check(constraint))
        }
        return result
    }
}

enum class PetSize {
    SMALL,
    MEDIUM,
    BIG,
    UNDEFINED
}