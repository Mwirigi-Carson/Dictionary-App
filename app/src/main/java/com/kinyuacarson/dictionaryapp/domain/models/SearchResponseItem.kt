package com.kinyuacarson.dictionaryapp.domain.models

data class SearchResponseItem(
    val meanings : List<Meaning>,
    val phonetic : String,
    val word : String
)
