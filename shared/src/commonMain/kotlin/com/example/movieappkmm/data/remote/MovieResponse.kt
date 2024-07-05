package com.example.movieappkmm.data.remote

@kotlinx.serialization.Serializable
internal data class MovieResponse(
    val results: List<MovieDataResponse>
)