package com.example.movieappkmm.data.util

import com.example.movieappkmm.data.remote.MovieDataResponse
import com.example.movieappkmm.domain.model.Movie

internal fun MovieDataResponse.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        description = overview,
        imageUrl = getImageUrl(posterImage),
        releaseDate = releaseDate
    )
}

private fun getImageUrl(posterImage: String) = "https://image.tmdb.org/t/p/w500/$posterImage"