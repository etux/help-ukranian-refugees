package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

interface Constrained {
    fun check(constraint: Constraint): ConstraintResult {
        return constraint.accepts(this)
    }

    fun check(constrains: Constraints): ConstraintResult {
        return constrains.check(this)
    }
}

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Constraint : AbstractJpaPersistable<UUID>() {

    fun accepts(visitor: Constrained): ConstraintResult {
        return visitor.check(this)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        return other.javaClass == this.javaClass
    }

    override fun hashCode() = this.javaClass.hashCode()

    companion object {
        inline fun newInstance(javaClassName: String): Constraint {
            return Class
                .forName(javaClassName)
                .asSubclass(Constraint::class.java)
                .getDeclaredConstructor()
                .newInstance()
                ?: run { throw RuntimeException("Illegal class name $javaClassName")}
        }
    }
}

class Constraints (
    private val constraints: Set<Constraint> = emptySet()
): Set<Constraint> by constraints, Constraint() {

    fun check(visitor: Constrained): CombinedConstraintResult {
        return constraints
            .map { it.accepts(visitor) }
            .fold(CombinedConstraintResult(visitor)) {
                    acc, result -> acc.merge(result)
            }
    }
}

class PetAllowedConstraint: Constraint()
class ManAllowedConstraint: Constraint()
class WomanAllowedConstraint: Constraint()
class ChildAllowedConstraint: Constraint()
class LgbtqAllowedConstraint: Constraint()
class PoCAllowedConstraint: Constraint()
class ElderAllowedConstraint: Constraint()
class HandicapAllowedConstraint: Constraint()


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