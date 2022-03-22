package org.help.ukraine.hosting.services.hosting

import org.help.ukraine.hosting.domain.model.Guest
import org.help.ukraine.hosting.domain.model.Person
import org.help.ukraine.hosting.domain.model.Request
import org.help.ukraine.hosting.domain.repositories.PetRepository
import org.help.ukraine.hosting.domain.repositories.GuestRepository
import org.help.ukraine.hosting.domain.repositories.RequestRepository
import org.springframework.stereotype.Service

@Service
class GuestRequestService(
    private val requestRepository: RequestRepository,
    private val guestRepository: GuestRepository,
    private val petRepository: PetRepository,
) {

    fun register(request: Request): Request {
        return requestRepository.save(
            Request(
                serviceType = request.serviceType,
                availability = request.availability,
            ).also {
                toBePersisted ->
                    request.guests.map { register(it, toBePersisted) }
                    request.pets.forEach { toBePersisted.pets += it }

            }
        )
    }

    private fun <P: Person<P>> register(
        guest: Guest<P>,
        request: Request
    ): Guest<P> {
        val persistedGuest = guestRepository.save(guest)
        request.guests += persistedGuest
        return persistedGuest
    }

    fun cancel(request: Request): Boolean {
        return true
    }
}