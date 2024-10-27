package com.rais.movies.usecase.toprated

import com.rais.movies.data.Resource
import com.rais.movies.model.MovieTopRated
import kotlinx.coroutines.flow.Flow

interface MovieTopRatedUseCase {
    fun getMovieTopRated(): Flow<Resource<List<MovieTopRated>>>
}