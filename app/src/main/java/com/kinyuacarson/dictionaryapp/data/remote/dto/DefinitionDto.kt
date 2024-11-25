package com.kinyuacarson.dictionaryapp.data.remote.dto

import com.kinyuacarson.dictionaryapp.domain.models.Definition

data class DefinitionDto(
    val definition: String? = null,
    val example: String? = null,
)

fun definitionDtoToDefinition(definitionDto: DefinitionDto?) : Definition {
    return Definition(
        definition = definitionDto?.definition ?: "",
        example = definitionDto?.example ?: ""
    )
}


//fun DefinitionDto.toDefinition() : Definition {
//    return Definition (
//        definition = definition ?: "",
//        example = example ?: ""
//    )
//}