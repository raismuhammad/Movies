package com.rais.movies.data.repository

import com.rais.movies.data.Resource
import com.rais.movies.data.local.LocalDataSource
import com.rais.movies.data.remote.ApiResponse
import com.rais.movies.data.remote.response.NowPlayingResponse
import com.rais.movies.data.remote.response.SearchMovieResponse
import com.rais.movies.data.remote.response.TopRatedResponse
import com.rais.movies.data.remote.source.NetworkBoundResource
import com.rais.movies.data.remote.source.RemoteDataSource
import com.rais.movies.mapper.MovieNowPlayingMapper
import com.rais.movies.mapper.MovieTopRatedMapper
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.model.MovieTopRated
import com.rais.movies.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieNowPlayingRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExcutors: AppExecutors
) {

    fun getMovieNowPlaying(): Flow<Resource<List<MovieNowPlaying>>> =
        object : NetworkBoundResource<List<MovieNowPlaying>, NowPlayingResponse>() {
            override fun loadFromDB(): Flow<List<MovieNowPlaying>> {
                return localDataSource.getMoviesNowPlaying().map {
                    MovieNowPlayingMapper.mapEntityToModel(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<NowPlayingResponse>> {
                return remoteDataSource.getMovieNowPlaying()
            }

            override suspend fun saveCallResult(data: NowPlayingResponse) {
                val movieListNowPlaying = MovieNowPlayingMapper.mapResponseToEntity(data)
                localDataSource.insertMovieNowPlaying(movieListNowPlaying)
            }

            override fun shouldFetch(data: List<MovieNowPlaying>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.asFlow()

    fun getFavoriteMovie(): Flow<List<MovieNowPlaying>> {
        return localDataSource.getFavoriteMoviesNowPlaying().map {
            MovieNowPlayingMapper.mapEntityToModel(it)
        }
    }

    fun setFavoriteMovie(movie: MovieNowPlaying, state: Boolean) {
        val moviesEntity = MovieNowPlayingMapper.mapModelToEntity(movie)
        appExcutors.diskIO().execute { localDataSource.setFavoriteMoviesNowPlaying(moviesEntity, state) }
    }

    fun getMovieById(id: Int?): Flow<MovieNowPlaying> {
        return localDataSource.getMovieByIdNowPlaying(id).map {
            MovieNowPlayingMapper.mapDetailEntityToDetailModel(it)
        }
    }

    fun searchMovie(q: String?): Flow<Resource<SearchMovieResponse>> {
        return remoteDataSource.getSearchMovie(q)
    }
}