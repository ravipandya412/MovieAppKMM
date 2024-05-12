package com.example.movieappkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform