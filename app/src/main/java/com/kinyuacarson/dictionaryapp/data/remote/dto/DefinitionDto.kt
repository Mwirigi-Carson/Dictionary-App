package com.kinyuacarson.dictionaryapp.data.remote.dto

import com.kinyuacarson.dictionaryapp.domain.models.Definition

data class DefinitionDto(
    val definition: String? = null,
    val example: String? = null,
)

fun DefinitionDto.toDefinition() : Definition {
    return Definition (
        definition = definition ?: "",
        example = example ?: ""
    )
}