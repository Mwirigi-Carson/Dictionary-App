package com.kinyuacarson.dictionaryapp.presentation

import com.kinyuacarson.dictionaryapp.domain.models.SearchResponseItem

data class MainUIState(
    val isLoading : Boolean = false,
    val searchWord : String = "",
    val searchResponseItem : SearchResponseItem? = null
)
