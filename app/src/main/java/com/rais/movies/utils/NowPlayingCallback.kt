package com.rais.movies.utils

import com.rais.movies.model.MovieNowPlaying

interface NowPlayingCallback {
    fun onClickMovie(item: MovieNowPlaying)
}