package com.rais.movies.mapper

import com.rais.movies.data.local.entity.MoviesTopRatedEntity
import com.rais.movies.data.remote.response.TopRatedResponse
import com.rais.movies.model.MovieTopRated

object MovieTopRatedMapper {

    fun mapResponseToEntity(input: TopRatedResponse): List<MoviesTopRatedEntity> {
        val arrayListEntity = ArrayList<MoviesTopRatedEntity>()
        input.results?.map {
            it?.let {
                arrayListEntity.add(
                    MoviesTopRatedEntity(
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

    fun mapEntityToModel(input: List<MoviesTopRatedEntity>): List<MovieTopRated> =
        input.map {
            MovieTopRated(
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

//    fun mapDetailEntityToDetailDomain(input: MoviesEntity): Movie =
//        Movie(
//            id = input.id,
//            title = input.title,
//            original_title = input.original_title,
//            poster_path = input.poster_path,
//            backdrop_path = input.backdrop_path,
//            release_date = input.release_date,
//            overview = input.overview,
//            popularity = input.popularity,
//            vote_average = input.vote_average,
//            isLike = input.isLike
//        )
//
//    fun mapDomainToEntity(input: Movie): MoviesEntity =
//        MoviesEntity(
//            id = input.id,
//            title = input.title,
//            original_title = input.original_title,
//            poster_path = input.poster_path,
//            backdrop_path = input.backdrop_path,
//            release_date = input.release_date,
//            overview = input.overview,
//            popularity = input.popularity,
//            vote_average = input.vote_average,
//            isLike = input.isLike
//        )
}