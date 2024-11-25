package com.kinyuacarson.dictionaryapp.data.remote.dto

import com.kinyuacarson.dictionaryapp.domain.models.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>? = null,
    val partOfSpeech: String? = null,
)

fun MeaningDto.toMeaning() : Meaning {
    return Meaning(
        definition = definitionDtoToDefinition(definitions?.get(0)),
        partOfSpeech = partOfSpeech ?: ""
    )
}