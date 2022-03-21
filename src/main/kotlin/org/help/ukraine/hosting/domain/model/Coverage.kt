package org.help.ukraine.hosting.domain.model

abstract class Coverage<T, K>(
    open val offer: T,
    open val demand: K,
    open val score: Int
) {
    abstract fun calculate(): Coverage<T,K>
}