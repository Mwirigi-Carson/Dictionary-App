package com.kinyuacarson.dictionaryapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinyuacarson.dictionaryapp.domain.repository.DictionaryRepository
import com.kinyuacarson.dictionaryapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val repository: DictionaryRepository
) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUIState())
    val mainState = _mainUiState.asStateFlow()

    val job : Job? = null

    fun onEvent ( mainUIEvent : MainUIEvents ) {
        when (mainUIEvent) {
            MainUIEvents.OnSearchClick -> {
                loadWordResult()
            }
            is MainUIEvents.OnSearchWordChange -> {
                _mainUiState.update {
                    it.copy(searchWord = mainUIEvent.newWord.lowercase())
                }
            }
        }
    }

    private fun loadWordResult(){
        viewModelScope.launch {
            repository.searchWord(
                _mainUiState.value.searchWord
            ).collect { result ->
                when (result){
                    is Result.Error -> Unit
                    is Result.Loading -> {
                        _mainUiState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Result.Success -> {
                        result.data?.let { searchResponseItem ->
                            _mainUiState.update {
                                it.copy(searchResponseItem = searchResponseItem)
                            }
                        }
                    }
                }
            }
        }
    }

}