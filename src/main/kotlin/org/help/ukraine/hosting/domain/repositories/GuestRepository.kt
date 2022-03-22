package org.help.ukraine.hosting.domain.repositories

import org.help.ukraine.hosting.domain.model.Guest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GuestRepository : JpaRepository<Guest<*>, UUID> {
}