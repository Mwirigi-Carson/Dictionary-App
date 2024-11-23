package com.kinyuacarson.dictionaryapp.presentation

import kotlinx.coroutines.Job

sealed class MainUIEvents {
    data class OnSearchWordChange (val newWord : String) : MainUIEvents()
    data object OnSearchClick : MainUIEvents()
}