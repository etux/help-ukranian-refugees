package org.help.ukraine.hosting.domain.model

sealed class AgeRange(
    val intRange: IntRange
): ClosedRange<Int> by intRange, Iterable<Int> by intRange {
    object BabyAge : AgeRange(IntRange(0, 3))
    object ToddlerAge : AgeRange(IntRange(3, 5))
    object KidAge : AgeRange(IntRange(3, 5))
    object TeenagerAge : AgeRange(IntRange(13, 18))
    object AdultAge : AgeRange(IntRange(18, 100))

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        return other.javaClass == this.javaClass
    }

    override fun hashCode() = this.javaClass.hashCode()

    companion object {
        fun withAge(age: Int): AgeRange {
            if (BabyAge.intRange.contains(age)) return BabyAge
            else if (ToddlerAge.intRange.contains(age)) return ToddlerAge
            else if (KidAge.intRange.contains(age)) return KidAge
            else if (TeenagerAge.intRange.contains(age)) return TeenagerAge
            else return AdultAge
        }
    }

    object UNDEFINED : AgeRange(IntRange(-1,0))
}