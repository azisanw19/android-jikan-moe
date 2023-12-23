package com.canwar.base.ui.compose.anime

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canwar.base.model.app.Anime
import com.canwar.base.repository.AnimeRepository
import com.canwar.base.utils.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private val _isLoading: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isLoading get() = _isLoading.asSharedFlow()

    private val _errorMessage: MutableSharedFlow<String> = MutableSharedFlow()
    val errorMessage get() = _errorMessage.asSharedFlow()

    private val _data: MutableSharedFlow<List<Anime>> = MutableSharedFlow()
    val data get() = _data.asSharedFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getAnime() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler ) {
            animeRepository.getAnimeSearch().collect {
                Log.d("AnimeViewModel", "getAnime: $it")
                when (it) {
                    is DataState.Loading -> _isLoading.emit(true)
                    is DataState.Error -> {
                        _isLoading.emit(false)
                        _errorMessage.emit(it.exception.message ?: "")
                    }
                    is DataState.Success -> {
                        _isLoading.emit(false)
                        _data.emit(it.data ?: listOf())
                    }
                }
            }
        }
    }

}