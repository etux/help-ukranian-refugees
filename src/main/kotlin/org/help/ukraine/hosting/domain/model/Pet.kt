package org.help.ukraine.hosting.domain.model

import java.util.*

sealed class Pet(
    private val id: UUID = UUID.randomUUID(),
    private val constraints: Constraints<*>,
    private val gender: Gender,
    private val size: PetSize
): Constrained() {
    fun isAccepted(check: Constraints<*>): ConstraintResult {
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

class Cat(
    gender: Gender,
    size: PetSize,
    constraints: Constraints<Cat>
): Pet(constraints = constraints, gender = gender, size = size)

class Dog(
    gender: Gender,
    size: PetSize,
    constraints: Constraints<Dog>
): Pet(constraints = constraints, gender = gender, size = size)
