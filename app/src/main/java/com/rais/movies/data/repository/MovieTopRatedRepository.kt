package com.rais.movies.data.repository

import com.rais.movies.data.Resource
import com.rais.movies.data.local.LocalDataSource
import com.rais.movies.data.remote.ApiResponse
import com.rais.movies.data.remote.response.TopRatedResponse
import com.rais.movies.data.remote.source.NetworkBoundResource
import com.rais.movies.data.remote.source.RemoteDataSource
import com.rais.movies.mapper.MovieTopRatedMapper
import com.rais.movies.model.MovieTopRated
import com.rais.movies.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieTopRatedRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExcutors: AppExecutors
) {

    fun getMovieTopRated(): Flow<Resource<List<MovieTopRated>>> =
        object : NetworkBoundResource<List<MovieTopRated>, TopRatedResponse>() {
            override fun loadFromDB(): Flow<List<MovieTopRated>> {
                return localDataSource.getMoviesTopRated().map {
                    MovieTopRatedMapper.mapEntityToModel(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<TopRatedResponse>> {
                return remoteDataSource.getMovieTopRated()
            }

            override suspend fun saveCallResult(data: TopRatedResponse) {
                val movieListTopRated = MovieTopRatedMapper.mapResponseToEntity(data)
                localDataSource.insertMovieTopRated(movieListTopRated)
            }

            override fun shouldFetch(data: List<MovieTopRated>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.asFlow()
}