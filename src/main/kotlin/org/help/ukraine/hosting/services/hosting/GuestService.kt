package org.help.ukraine.hosting.services.hosting

import org.help.ukraine.hosting.domain.model.Request
import org.help.ukraine.hosting.services.matching.MatchProducer

class GuestService {
    fun register(request: Request): Request {
        return request
    }

    fun cancel(request: Request): Boolean {
        return true
    }
}