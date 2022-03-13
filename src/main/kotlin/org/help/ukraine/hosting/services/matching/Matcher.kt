package org.help.ukraine.hosting.services.matching

import org.help.ukraine.hosting.domain.model.Match

interface Matcher {
    fun execute(): Set<Match>
}
