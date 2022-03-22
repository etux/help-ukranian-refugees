package org.help.ukraine.hosting.domain.repositories

import org.help.ukraine.hosting.domain.model.Request
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RequestRepository : JpaRepository<Request, UUID> {

}
