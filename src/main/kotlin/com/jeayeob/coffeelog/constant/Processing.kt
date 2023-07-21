package com.jeayeob.coffeelog.constant

import com.fasterxml.jackson.annotation.JsonValue
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

enum class Processing (
    @get:JsonValue
    val value: String,

) {
    WASHED("Washed"),
    NATURAL("Natural"),
    HONEY("Honey"),
    OTHER("Other");

    companion object {
        fun parse(value: String?): Processing? {
            return Processing.values().firstOrNull { it.value == value }
        }
    }

    @Converter(autoApply = true)
    class ProcessingConverter : AttributeConverter<Processing?, String?> {

        override fun convertToDatabaseColumn(attribute: Processing?): String? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: String?): Processing? {
            return dbData?.let { Processing.parse(it) }
        }
    }
}