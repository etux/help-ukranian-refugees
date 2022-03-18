package org.help.ukraine.hosting.domain.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractJpaPersistable<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private var id: T? = null

    fun getId(): T? {
        return id
    }
}