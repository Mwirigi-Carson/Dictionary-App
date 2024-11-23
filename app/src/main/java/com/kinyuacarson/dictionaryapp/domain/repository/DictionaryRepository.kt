package com.kinyuacarson.dictionaryapp.domain.repository

import com.kinyuacarson.dictionaryapp.domain.models.SearchResponseItem
import com.kinyuacarson.dictionaryapp.util.Result
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    suspend fun searchWord(
        word : String
    ) : Flow<Result<SearchResponseItem>>
}