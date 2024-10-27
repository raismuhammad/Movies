package com.rais.movies.usecase.nowplaying

import com.rais.movies.data.Resource
import com.rais.movies.data.remote.ApiResponse
import com.rais.movies.data.remote.response.SearchMovieResponse
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.model.MovieTopRated
import kotlinx.coroutines.flow.Flow

interface MovieNowPlayingUseCase {
    fun getMovieNowPlaying(): Flow<Resource<List<MovieNowPlaying>>>
    fun getFavoriteMoviesNowPlaying() : Flow<List<MovieNowPlaying>>
    fun setFavoriteMoviesNowPlaying(movie : MovieNowPlaying, state: Boolean)
    fun getMovieByIdNowPlaying(id: Int?) : Flow<MovieNowPlaying>
    fun getSearchMovie(q: String?) : Flow<Resource<SearchMovieResponse>>
}