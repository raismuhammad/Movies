package com.rais.movies.viewmodel.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rais.movies.usecase.nowplaying.MovieNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieNowPlayingUseCase: MovieNowPlayingUseCase
): ViewModel() {

    val moviesFavorite = movieNowPlayingUseCase.getFavoriteMoviesNowPlaying().asLiveData()
}