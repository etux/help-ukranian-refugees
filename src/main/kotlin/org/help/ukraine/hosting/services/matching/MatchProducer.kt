package org.help.ukraine.hosting.services.matching

import org.help.ukraine.hosting.domain.model.Match

interface MatchProducer {
    fun match(matcher: Matcher): Set<Match>
}
