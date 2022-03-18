package org.help.ukraine.hosting.domain.jpa.converters

import org.help.ukraine.hosting.domain.model.Constraint
import org.help.ukraine.hosting.domain.model.Constraints
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class ConstraintsConverter(val singleConverter: ConstraintConverter): AttributeConverter<Constraints, Set<String>> {
    override fun convertToDatabaseColumn(attribute: Constraints): Set<String> {
        return attribute.map{ singleConverter.convertToDatabaseColumn(it) }.toSet()
    }

    override fun convertToEntityAttribute(dbData: Set<String>): Constraints {
        val result:Set<Constraint> = dbData.map{ singleConverter.convertToEntityAttribute(it) }.toSet()
        return Constraints(constraints = result)
    }

}
