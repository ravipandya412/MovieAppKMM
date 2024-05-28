package com.example.movieappkmm.android.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieappkmm.domain.model.Movie

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    loadNextMovies: (Boolean) -> Unit,
    navigateToDetail: (Movie) -> Unit
) {

}