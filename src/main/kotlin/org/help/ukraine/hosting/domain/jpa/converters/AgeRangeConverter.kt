package org.help.ukraine.hosting.domain.jpa.converters

import org.help.ukraine.hosting.domain.model.AgeRange
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class AgeRangeConverter: AttributeConverter<AgeRange, String> {
    override fun convertToDatabaseColumn(attribute: AgeRange): String {
        return when(attribute) {
            is AgeRange.BabyAge -> AgeRangeEnum.BABY.toString().lowercase()
            is AgeRange.ToddlerAge -> AgeRangeEnum.TODDLER.toString().lowercase()
            is AgeRange.KidAge -> AgeRangeEnum.KID.toString().lowercase()
            is AgeRange.TeenagerAge -> AgeRangeEnum.TEENAGER.toString().lowercase()
            is AgeRange.AdultAge -> AgeRangeEnum.ADULT.toString().lowercase()
            is AgeRange.UNDEFINED -> AgeRangeEnum.UNDEFINED.toString().lowercase()
        }
    }

    override fun convertToEntityAttribute(dbData: String): AgeRange {
        val value = AgeRangeEnum.valueOf(dbData.uppercase())
        if (value == AgeRangeEnum.BABY) return  AgeRange.BabyAge
        if (value == AgeRangeEnum.TODDLER) return AgeRange.ToddlerAge
        if (value == AgeRangeEnum.KID) return AgeRange.KidAge
        if (value == AgeRangeEnum.TEENAGER) return AgeRange.TeenagerAge
        if (value == AgeRangeEnum.ADULT) return AgeRange.AdultAge
        else return AgeRange.UNDEFINED
    }

    enum class AgeRangeEnum{
        BABY, TODDLER, KID, TEENAGER, ADULT, UNDEFINED;
    }
}