package org.help.ukraine.hosting.domain.model

open class Constrained {
    fun check(constraint: Constraint<*>): ConstraintResult {
        return constraint.accepts(this)
    }
}

sealed class Constraint<T>(val clazz: Class<T>) {
    fun accepts(visitor: Constrained): ConstraintResult {
        return visitor.check(this)
    }
}

class Constraints<T: Any> (
    private val constraints: Set<Constraint<T>> = emptySet()
): Set<Constraint<T>> by constraints {

    fun accepts(visitor: Constrained): CombinedConstraintResult {
        return constraints
            .map { it.accepts(visitor) }
            .fold(CombinedConstraintResult(visitor)) {
                    acc, result -> acc.merge(result)
            }
    }
}

class CatsAdmittedConstraint: Constraint<Cat>(clazz = Cat::class.java)

class CatsNotAllowedConstraint: Constraint<Cat>(clazz = Cat::class.java)

sealed class ConstraintResult(open val visitor: Any)  {
    open fun merge(that: ConstraintResult): ConstraintResult {
        return when(this) {
            is PositiveConstraintResult ->
                when (that) {
                    is PositiveConstraintResult -> that
                    is NegativeConstraintResult -> that
                    is AbstainConstraintResult -> this
                    is CombinedConstraintResult -> throw RuntimeException()

                }
            is NegativeConstraintResult -> return this
            is AbstainConstraintResult -> return that
            is CombinedConstraintResult -> throw RuntimeException()
        }
    }
}

class PositiveConstraintResult(visitor: Any): ConstraintResult(visitor = visitor)

class NegativeConstraintResult(visitor: Any): ConstraintResult(visitor = visitor)

class AbstainConstraintResult(visitor: Any): ConstraintResult(visitor = visitor)

class CombinedConstraintResult(
    override val visitor: Any,
    private val results: Set<ConstraintResult> = mutableSetOf(),
    private var finalResult: ConstraintResult = AbstainConstraintResult(visitor)
): ConstraintResult(visitor = visitor) {

    override fun merge(that: ConstraintResult): CombinedConstraintResult {
        return CombinedConstraintResult(visitor, results + that, finalResult)
    }
}