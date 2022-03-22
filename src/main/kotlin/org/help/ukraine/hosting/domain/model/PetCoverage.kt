package org.help.ukraine.hosting.domain.model

class PetCoverage(
    override val offer: Space,
    override val demand: Set<Pet>,
    override val score: Int = 0
) : Coverage<Space, Set<Pet>>(offer, demand, score) {

    override fun calculate(): PetCoverage {
        val nonAcceptedPets = demand.toMutableSet()

        demand.forEach { pet ->
            if (offer.allows(pet)) {
                nonAcceptedPets.remove(pet)
            }
        }

        return PetCoverage(
            offer = offer,
            demand = demand,
            score = 100 - ((nonAcceptedPets.size / demand.size) * 100)
        )
    }
}
