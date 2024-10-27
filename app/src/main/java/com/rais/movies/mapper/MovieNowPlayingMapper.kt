package com.rais.movies.mapper

import com.rais.movies.data.local.entity.MoviesNowPlayingEntity
import com.rais.movies.data.local.entity.MoviesTopRatedEntity
import com.rais.movies.data.remote.response.NowPlayingResponse
import com.rais.movies.data.remote.response.TopRatedResponse
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.model.MovieTopRated

object MovieNowPlayingMapper {

    fun mapResponseToEntity(input: NowPlayingResponse): List<MoviesNowPlayingEntity> {
        val arrayListEntity = ArrayList<MoviesNowPlayingEntity>()
        input.results?.map {
            it?.let {
                arrayListEntity.add(
                    MoviesNowPlayingEntity(
                        idMovie = it.id,
                        title = it.title,
                        original_title = it.original_title,
                        poster_path = it.poster_path,
                        backdrop_path = it.backdrop_path,
                        release_date = it.release_date,
                        overview = it.overview,
                        popularity = it.popularity,
                        vote_average = it.vote_average
                    )
                )
            }
        }
        return arrayListEntity
    }

    fun mapEntityToModel(input: List<MoviesNowPlayingEntity>): List<MovieNowPlaying> =
        input.map {
            MovieNowPlaying(
                idMovie = it.idMovie,
                title = it.title,
                original_title = it.original_title,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                release_date = it.release_date,
                overview = it.overview,
                popularity = it.popularity,
                vote_average = it.vote_average,
                isLike = it.isLike,
            )
        }

    fun mapDetailEntityToDetailModel(input: MoviesNowPlayingEntity): MovieNowPlaying =
        MovieNowPlaying(
            idMovie = input.idMovie,
            title = input.title,
            original_title = input.original_title,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            release_date = input.release_date,
            overview = input.overview,
            popularity = input.popularity,
            vote_average = input.vote_average,
            isLike = input.isLike
        )

    fun mapModelToEntity(input: MovieNowPlaying): MoviesNowPlayingEntity =
    MoviesNowPlayingEntity(
            idMovie = input.idMovie,
            title = input.title,
            original_title = input.original_title,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            release_date = input.release_date,
            overview = input.overview,
            popularity = input.popularity,
            vote_average = input.vote_average,
            isLike = input.isLike
        )
}