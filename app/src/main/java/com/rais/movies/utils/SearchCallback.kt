package com.rais.movies.utils

import com.rais.movies.data.remote.response.SearchMovieResult
import com.rais.movies.model.MovieNowPlaying

interface SearchCallback {
    fun onClickMovie(item: SearchMovieResult)
}