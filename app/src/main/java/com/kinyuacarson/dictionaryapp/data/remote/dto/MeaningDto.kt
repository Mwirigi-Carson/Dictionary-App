package com.kinyuacarson.dictionaryapp.data.remote.dto

import com.kinyuacarson.dictionaryapp.domain.models.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>? = null,
    val partOfSpeech: String? = null,
)

fun MeaningDto.toMeaning() : Meaning {
    return Meaning(
        definition = definitions?.get(0)!!.toDefinition(),
        partOfSpeech = partOfSpeech ?: ""
    )
}