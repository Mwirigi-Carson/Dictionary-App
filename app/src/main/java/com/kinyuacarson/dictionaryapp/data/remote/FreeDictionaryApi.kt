package com.kinyuacarson.dictionaryapp.data.remote

import com.kinyuacarson.dictionaryapp.data.remote.dto.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FreeDictionaryApi {
    @GET("{word}")
    suspend fun searchWord(
        @Path("word") word : String
    ) : SearchResponseDto

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"
    }
}