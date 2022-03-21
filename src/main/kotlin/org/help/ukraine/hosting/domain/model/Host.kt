package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Host<T: Person<T>> (
    person: T,
    offer: HostingOffer
): AbstractJpaPersistable<UUID>()

@Entity
class AdultHost(
    person: Adult,
    offer: HostingOffer
): Host<Adult>(person = person, offer = offer)

@Entity
class MinorHost(
    person: Minor,
    offer: HostingOffer
): Host<Minor>(person = person, offer = offer)