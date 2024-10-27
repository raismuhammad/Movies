package com.rais.movies.viewmodel.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.usecase.nowplaying.MovieNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieNowPlayingViewModel @Inject constructor(private val movieNowPlayingUseCase: MovieNowPlayingUseCase) : ViewModel() {
    fun getDetailMovie(id: Int?) : LiveData<MovieNowPlaying> {
        return movieNowPlayingUseCase.getMovieByIdNowPlaying(id).asLiveData()
    }

    fun setFavoriteMovie(movie: MovieNowPlaying, state: Boolean) = movieNowPlayingUseCase.setFavoriteMoviesNowPlaying(movie, state)

}