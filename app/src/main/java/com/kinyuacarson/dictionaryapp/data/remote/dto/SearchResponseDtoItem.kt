package com.kinyuacarson.dictionaryapp.data.remote.dto

import com.kinyuacarson.dictionaryapp.domain.models.SearchResponseItem

data class SearchResponseDtoItem(
    val meanings: List<MeaningDto>,
    val phonetic: String? = null,
    val word: String? = null
)

fun SearchResponseDtoItem.toSearchResponseItem() : SearchResponseItem {
    return SearchResponseItem(
        meanings = meanings.map {
            it.toMeaning()
        },
        phonetic = phonetic ?: "",
        word = word ?: ""
    )
}
