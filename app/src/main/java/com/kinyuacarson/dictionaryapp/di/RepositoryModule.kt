package com.kinyuacarson.dictionaryapp.di

import com.kinyuacarson.dictionaryapp.data.repository.DictionaryRepositoryImpl
import com.kinyuacarson.dictionaryapp.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsDictionaryRepository(
        dictionaryRepositoryImpl: DictionaryRepositoryImpl
    ) : DictionaryRepository
}