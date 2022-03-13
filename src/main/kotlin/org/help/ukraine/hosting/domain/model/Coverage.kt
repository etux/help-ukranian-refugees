package org.help.ukraine.hosting.domain.model

class Coverage(
    val type: CoverageType,
)

enum class CoverageType {
    CLOSED,
    START_OPENED,
    END_OPENED,
    OPEN
}