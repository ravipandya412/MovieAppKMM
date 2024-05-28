package com.example.movieappkmm.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkmm.domain.model.Movie
import com.example.movieappkmm.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    var uiState by mutableStateOf(HomeScreenState())
    private var currentPage = 1


    init {
        loadMovies(forceReload = false)
    }

    fun loadMovies(forceReload: Boolean = false) {
        if (uiState.loading) return
        if (forceReload) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refresh = true)

        viewModelScope.launch {
            uiState = uiState.copy(
                loading = true
            )

            try {
                val result = getMoviesUseCase(page = currentPage)
                val movies = if (currentPage == 1) result else uiState.movies + result

                currentPage += 1

                uiState = uiState.copy(
                    loading = false,
                    refresh = false,
                    loadFinished = result.isEmpty(),
                    movies = movies
                )
            } catch (error: Throwable) {
                uiState = uiState.copy(
                    loading = false,
                    refresh = false,
                    loadFinished = true,
                    errorMessage = error.localizedMessage
                )
            }
        }
    }
}


data class HomeScreenState(
    var loading: Boolean = false,
    var refresh: Boolean = false,
    var movies: List<Movie> = listOf(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false
)