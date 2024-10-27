package com.rais.movies.viewmodel.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rais.movies.data.Resource
import com.rais.movies.data.remote.ApiResponse
import com.rais.movies.data.remote.response.SearchMovieResponse
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.usecase.nowplaying.MovieNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(private val movieNowPlayingUseCase: MovieNowPlayingUseCase): ViewModel() {
    fun searchMovie(q: String?): LiveData<Resource<SearchMovieResponse>> {
        return movieNowPlayingUseCase.getSearchMovie(q).asLiveData()
    }

}