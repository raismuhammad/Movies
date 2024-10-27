package com.rais.movies.data.remote

import com.rais.movies.data.remote.response.MovieDetailResponse
import com.rais.movies.data.remote.response.NowPlayingResponse
import com.rais.movies.data.remote.response.SearchMovieResponse
import com.rais.movies.data.remote.response.TopRatedResponse
import com.rais.movies.utils.Constants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type:application/json")
    @GET("movie/top_rated?api_key=${Constants.API_KEY}")
    suspend fun movieTopRated(
    ) : TopRatedResponse

    @Headers("Content-Type:application/json")
    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(
    ) : NowPlayingResponse

    @Headers("Content-Type:application/json")
    @GET("/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String
    ) : MovieDetailResponse

    @Headers("Content-Type:application/json")
    @GET("search/movie")
    suspend fun getSearch(
        @Query("query") query: String?
    ) : SearchMovieResponse

}