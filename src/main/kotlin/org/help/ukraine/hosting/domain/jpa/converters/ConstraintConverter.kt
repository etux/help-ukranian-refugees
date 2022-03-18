package org.help.ukraine.hosting.domain.jpa.converters

import org.help.ukraine.hosting.domain.model.Constraint
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class ConstraintConverter: AttributeConverter<Constraint, String> {

    override fun convertToDatabaseColumn(attribute: Constraint): String {
        return attribute::class.java.name
    }

    override fun convertToEntityAttribute(dbData: String): Constraint {
        return Constraint.newInstance(dbData)
    }

}
