package org.help.ukraine.hosting.domain.model

data class BedCoverage(
    override val offer: Space,
    override val demand: Set<Guest<*>>,
    override val score: Int = 0
): Coverage<Space, Set<Guest<*>>>(offer, demand, score) {

    override fun calculate(): BedCoverage {
        val remainingBeds = offer.beds.toMutableSet()
        val remainingPeople = demand.toMutableSet()

        demand.forEach { guest ->
            remainingBeds.forEach { bed ->
                if (guest.isCoveredBy(bed)) {
                    remainingPeople.remove(guest)
                    remainingBeds.remove(bed)
                }
            }
        }

        return BedCoverage(
            offer = offer,
            demand = demand,
            score = 100 - ((remainingPeople.size / demand.size) * 100)
        )
    }

}