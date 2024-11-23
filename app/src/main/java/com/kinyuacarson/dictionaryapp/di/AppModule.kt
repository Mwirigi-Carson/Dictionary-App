package com.kinyuacarson.dictionaryapp.di

import com.kinyuacarson.dictionaryapp.data.remote.FreeDictionaryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor = loggingInterceptor)
        .build()



    @Provides
    @Singleton
    fun providesFreeDictionaryApi() : FreeDictionaryApi {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(FreeDictionaryApi.BASE_URL)
            .build()
            .create(FreeDictionaryApi::class.java)
    }

}