package com.kinyuacarson.dictionaryapp.data.repository

import android.util.Log
import com.kinyuacarson.dictionaryapp.data.remote.FreeDictionaryApi
import com.kinyuacarson.dictionaryapp.data.remote.dto.toSearchResponseItem
import com.kinyuacarson.dictionaryapp.domain.models.SearchResponseItem
import com.kinyuacarson.dictionaryapp.domain.repository.DictionaryRepository
import com.kinyuacarson.dictionaryapp.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: FreeDictionaryApi
) : DictionaryRepository {
    override suspend fun searchWord(word: String): Flow<Result<SearchResponseItem>> {
        return flow{
            emit(Result.Loading(isLoading = true))

            val remoteSearchResponseDto = try {
                dictionaryApi.searchWord(word = word)
            } catch (e : IOException) {

                e.printStackTrace()
                emit(Result.Error( message = "An IOException has occurred!"))
                emit(Result.Loading(isLoading = false))
                return@flow

            } catch (e : HttpException) {

                e.printStackTrace()
                emit(Result.Error( message = "An HttpException has occurred!"))
                emit(Result.Loading(isLoading = false))
                return@flow

            } catch (e : Exception) {

                e.printStackTrace()
                emit(Result.Error( message = "An Exception has occurred!"))
                emit(Result.Loading(isLoading = false))
                return@flow

            }

            Log.d("Repository Implementation", remoteSearchResponseDto.toString())

            remoteSearchResponseDto?.let { searchResponseDto ->
                searchResponseDto[0].let { searchResponseItemDto ->
                    Log.d("repository-implementation", searchResponseItemDto.toString())
                    emit(Result.Success(data = searchResponseItemDto?.toSearchResponseItem()))
                    emit(Result.Loading(isLoading = false))
                    return@flow
                }
            }

            emit(Result.Error(message = "An Error occurred!"))
            emit(Result.Loading(isLoading = false))
        }
    }
}