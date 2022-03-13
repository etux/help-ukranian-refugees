package org.help.ukraine.hosting.domain.model

class Child(
    override val ageRange: AgeRange,
    override val gender: Gender,
    override val constraints: Constraints<Person>,
    override val languages: Set<Language>
) : Person (
    ageRange = ageRange,
    gender = gender,
    constraints = constraints,
    languages = languages
)

sealed class AgeRange(
    val intRange: IntRange
): ClosedRange<Int> by intRange, Iterable<Int> by intRange {
    object BabyAge : AgeRange(IntRange(0, 3))
    object ToddlerAge : AgeRange(IntRange(3, 5))
    object KidAge : AgeRange(IntRange(3, 5))
    object TeenagerAge : AgeRange(IntRange(13, 18))
    object AdultAge : AgeRange(IntRange(18, 100))

    companion object {
        fun withAge(age: Int): AgeRange {
            if (BabyAge.intRange.contains(age)) return BabyAge
            else if (ToddlerAge.intRange.contains(age)) return ToddlerAge
            else if (KidAge.intRange.contains(age)) return KidAge
            else if (TeenagerAge.intRange.contains(age)) return TeenagerAge
            else return AdultAge
        }
    }
}
