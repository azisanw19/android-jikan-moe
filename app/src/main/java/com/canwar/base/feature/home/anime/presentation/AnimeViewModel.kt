package com.canwar.base.feature.home.anime.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canwar.base.feature.home.anime.domain.model.AnimeData
import com.canwar.base.feature.home.anime.domain.AnimeRepository
import com.canwar.base.common.data.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()

    private val _errorMessage: MutableStateFlow<String> = MutableStateFlow("")
    val errorMessage get() = _errorMessage.asStateFlow()

    private val _data: MutableStateFlow<List<AnimeData>> = MutableStateFlow(emptyList())
    val data get() = _data.asStateFlow()

    init {
        getAnime()
    }

    private fun getAnime() {
        viewModelScope.launch {
            _isLoading.emit(true)

            when (val result = animeRepository.getAnimeSearch()) {
                is DataState.Error -> {
                    _errorMessage.emit(result.message)
                }

                is DataState.Success -> {
                    _data.emit(result.data)
                }
            }
            _isLoading.emit(false)

        }
    }

}