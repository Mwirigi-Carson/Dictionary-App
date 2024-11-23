package com.kinyuacarson.dictionaryapp.data.remote.dto

import com.kinyuacarson.dictionaryapp.domain.models.SearchResponseItem

data class SearchResponseDtoItem(
    val meaning: List<MeaningDto>? = null,
    val phonetic: String? = null,
    val word: String? = null
)

fun SearchResponseDtoItem.toSearchResponseItem() : SearchResponseItem {
    return SearchResponseItem(
        meanings = meaning?.map {
            it.toMeaning()
        } ?: emptyList(),
        phonetic = phonetic ?: "",
        word = word ?: ""
    )
}
