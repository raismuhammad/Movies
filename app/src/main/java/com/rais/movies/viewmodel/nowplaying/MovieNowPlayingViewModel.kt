package com.rais.movies.viewmodel.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rais.movies.usecase.nowplaying.MovieNowPlayingUseCase
import com.rais.movies.usecase.toprated.MovieTopRatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieNowPlayingViewModel @Inject constructor(
    private val movieNowPlayingUseCase: MovieNowPlayingUseCase) : ViewModel() {
    val movieNowPlaying = movieNowPlayingUseCase.getMovieNowPlaying().asLiveData()
}