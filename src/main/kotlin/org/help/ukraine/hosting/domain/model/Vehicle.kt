package org.help.ukraine.hosting.domain.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.Table

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "vehicles")
abstract class Vehicle : AbstractJpaPersistable<UUID>()