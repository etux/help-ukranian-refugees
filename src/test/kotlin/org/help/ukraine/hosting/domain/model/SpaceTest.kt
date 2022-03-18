package org.help.ukraine.hosting.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class SpaceTest {

    private lateinit var subject: Space

    @MethodSource("bedsCoverage")
    @ParameterizedTest
    fun coverage(people: People, beds: Set<Bed>, expectedScore: Int) {
        assertThat(Space(beds = beds, constraints = Constraints()).coverage(people)).isEqualTo(expectedScore)
    }

    companion object {
        @JvmStatic
        fun bedsCoverage() = Stream.of(
            Arguments.of(
                People(setOf(Guest(age = 18))),
                setOf(Bed(type = BedTypes.SingleBed, assignedPeople = emptySet())),
                100
            ),
            Arguments.of(
                People(setOf(Guest(age = 18))),
                setOf(Bed(type = BedTypes.Crib, assignedPeople = emptySet())),
                0
            )
        )
    }
}