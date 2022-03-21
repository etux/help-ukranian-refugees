package org.help.ukraine.hosting.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.help.ukraine.hosting.test.TestFactory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class SpaceTest {

    private lateinit var subject: Space

    @MethodSource("bedsCoverage")
    @ParameterizedTest
    fun coverage(people: Set<Guest<*>>, beds: Set<Bed>, expectedScore: Int) {
        Space(beds = beds).also {
            assertThat(it.cover(people))
                .isEqualTo(BedCoverage(it, people, expectedScore))
        }
    }

    companion object {
        @JvmStatic
        fun bedsCoverage() = Stream.of(
            Arguments.of(
                setOf(TestFactory.createAdult().toGuest(TestFactory.createRequest())),
                setOf(Bed(type = BedTypes.Single, assignedPeople = emptySet())),
                100
            ),
            Arguments.of(
                setOf(TestFactory.createAdult().toGuest(TestFactory.createRequest())),
                setOf(Bed(type = BedTypes.Crib, assignedPeople = emptySet())),
                0
            )
        )
    }
}