package org.help.ukraine.hosting.domain.jpa.converters

import org.help.ukraine.hosting.domain.model.AgeRange
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class AgeRangeConverter: AttributeConverter<AgeRange, String> {
    override fun convertToDatabaseColumn(attribute: AgeRange): String {
        return when(attribute) {
            is AgeRange.BabyAge -> AgeRangeEnum.BABY.toString()
            is AgeRange.ToddlerAge -> AgeRangeEnum.TODDLER.toString()
            is AgeRange.KidAge -> AgeRangeEnum.KID.toString()
            is AgeRange.TeenagerAge -> AgeRangeEnum.TEENAGER.toString()
            is AgeRange.AdultAge -> AgeRangeEnum.ADULT.toString()
        }
    }

    override fun convertToEntityAttribute(dbData: String): AgeRange {
        val value = AgeRangeEnum.valueOf(dbData)
        if (value == AgeRangeEnum.BABY) return  AgeRange.BabyAge
        if (value == AgeRangeEnum.TODDLER) return AgeRange.ToddlerAge
        if (value == AgeRangeEnum.KID) return AgeRange.KidAge
        if (value == AgeRangeEnum.TEENAGER) return AgeRange.TeenagerAge
        else  return AgeRange.AdultAge
    }

    enum class AgeRangeEnum{
        BABY, TODDLER, KID, TEENAGER, ADULT
    }
}