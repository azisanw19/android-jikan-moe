package com.canwar.base.presentatsion.compose.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState get() = _homeState.asStateFlow()


    fun setActionEvent(homeEvent: HomeEvent) {

        viewModelScope.launch {
            when (homeEvent) {
                is HomeEvent.NavigateTo -> {
                    _homeState.emit(
                        HomeState.NavigateTo(
                            homeScreenDestinations = homeEvent.homeScreenDestinations
                        )
                    )
                }
            }
        }
    }


}

sealed class HomeEvent {
    data class NavigateTo(
        val homeScreenDestinations: HomeScreenDestinations
    ) : HomeEvent()

}

sealed class HomeState {
    object Loading : HomeState()
    data class NavigateTo(
        val homeScreenDestinations: HomeScreenDestinations
    ) : HomeState()
}