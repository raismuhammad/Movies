package com.rais.movies.viewmodel.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rais.movies.usecase.toprated.MovieTopRatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieTopRatedViewModel @Inject constructor(
    private val movieTopRatedUseCase: MovieTopRatedUseCase) : ViewModel() {
    val movieTopRated = movieTopRatedUseCase.getMovieTopRated().asLiveData()
}