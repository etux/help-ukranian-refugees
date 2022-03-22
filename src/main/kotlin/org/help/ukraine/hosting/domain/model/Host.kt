package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
@DiscriminatorColumn(name = "type")
abstract class Host<T: Person<T>> (
    @ManyToOne
    val person: T,
    @OneToOne
    val offer: HostingOffer
): AbstractJpaPersistable<UUID>()

@Entity
@DiscriminatorValue("adult")
class AdultHost(
    @ManyToOne
    override val person: Adult,
    @OneToOne
    override val offer: HostingOffer
): Host<Adult>(person = person, offer = offer)

@Entity
@DiscriminatorValue("minor")
class MinorHost(
    @ManyToOne
    override val person: Minor,
    @OneToOne
    override val offer: HostingOffer
): Host<Minor>(person = person, offer = offer)