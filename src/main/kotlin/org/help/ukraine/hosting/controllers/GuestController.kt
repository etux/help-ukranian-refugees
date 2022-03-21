package org.help.ukraine.hosting.controllers

import org.help.ukraine.hosting.controllers.dtos.HostingRequestDto
import org.help.ukraine.hosting.controllers.mappers.GuestServiceMapper
import org.help.ukraine.hosting.services.hosting.GuestRequestService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/requests")
class GuestController(
    private val guestService: GuestRequestService,
    private val mapper: GuestServiceMapper
) {

    @PostMapping("/hosting")
    fun createHostingRequest(hostingRequestDto: HostingRequestDto) : HostingRequestDto {
        return guestService
            .register(mapper.map(hostingRequestDto))
            .let { mapper.map(it) }
    }


}