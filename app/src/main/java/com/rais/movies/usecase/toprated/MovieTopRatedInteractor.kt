package com.rais.movies.usecase.toprated

import com.rais.movies.data.Resource
import com.rais.movies.data.repository.MovieTopRatedRepository
import com.rais.movies.model.MovieTopRated
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieTopRatedInteractor @Inject constructor(private val movieTopRatedRepository: MovieTopRatedRepository): MovieTopRatedUseCase {
    override fun getMovieTopRated(): Flow<Resource<List<MovieTopRated>>> {
        return movieTopRatedRepository.getMovieTopRated()
    }
}