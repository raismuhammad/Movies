package com.rais.movies.usecase.nowplaying

import com.rais.movies.data.Resource
import com.rais.movies.data.remote.ApiResponse
import com.rais.movies.data.remote.response.SearchMovieResponse
import com.rais.movies.data.repository.MovieNowPlayingRepository
import com.rais.movies.model.MovieNowPlaying
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieNowPlayingInteractor @Inject constructor(private val movieNowPlayingRepository: MovieNowPlayingRepository): MovieNowPlayingUseCase {
    override fun getMovieNowPlaying(): Flow<Resource<List<MovieNowPlaying>>> = movieNowPlayingRepository.getMovieNowPlaying()

    override fun getFavoriteMoviesNowPlaying(): Flow<List<MovieNowPlaying>>  = movieNowPlayingRepository.getFavoriteMovie()

    override fun setFavoriteMoviesNowPlaying(movie: MovieNowPlaying, state: Boolean) = movieNowPlayingRepository.setFavoriteMovie(movie, state)

    override fun getMovieByIdNowPlaying(id: Int?): Flow<MovieNowPlaying> = movieNowPlayingRepository.getMovieById(id)

    override fun getSearchMovie(q: String?): Flow<Resource<SearchMovieResponse>> = movieNowPlayingRepository.searchMovie(q)
}