package org.help.ukraine.hosting.domain.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractJpaPersistable<T>(
    @field:Id @field:GeneratedValue(strategy = GenerationType.TABLE) val id: T? = null
)