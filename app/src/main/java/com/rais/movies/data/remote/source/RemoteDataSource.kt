package com.rais.movies.data.remote.source

import android.util.Log
import com.google.gson.Gson
import com.rais.movies.data.Resource
import com.rais.movies.data.remote.ApiResponse
import com.rais.movies.data.remote.ApiService
import com.rais.movies.data.remote.response.ErrorResponse
import com.rais.movies.data.remote.response.NowPlayingResponse
import com.rais.movies.data.remote.response.SearchMovieResponse
import com.rais.movies.data.remote.response.TopRatedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieTopRated(): Flow<ApiResponse<TopRatedResponse>> {
        return flow {
            try {
                val response = apiService.movieTopRated()
                val movie = response.results
                movie?.let {
                    if (it.isNotEmpty()) {
                        emit(ApiResponse.Success(response))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = parseErrorResponse(errorBody)
                emit(ApiResponse.Error(errorBody.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieNowPlaying(): Flow<ApiResponse<NowPlayingResponse>> {
        return flow {
            try {
                val response = apiService.getMovieNowPlaying()
                val movie = response.results
                movie?.let {
                    if (it.isNotEmpty()) {
                        emit(ApiResponse.Success(response))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = parseErrorResponse(errorBody)
                emit(ApiResponse.Error(errorBody.toString()))
            } catch (e: IOException) {
                println("Network Error: ${e.message}")
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getSearchMovie(q: String?): Flow<Resource<SearchMovieResponse>> {
        return flow {
            try {
                val response = apiService.getSearch(q)
                val movie = response.results
                if (response.results != null){
                    emit(Resource.Success(response))
                } else {
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = parseErrorResponse(errorBody)
                emit(Resource.Error(errorBody.toString()))
            } catch (e: IOException) {
                println("Network Error: ${e.message}")
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun parseErrorResponse(errorBody: String?): ErrorResponse {
        return if (errorBody != null) {
            // Parsing errorBody menjadi ErrorResponse
            Gson().fromJson(errorBody, ErrorResponse::class.java)
        } else {
            ErrorResponse("Unknown", "An unknown error occurred")
        }
    }
}