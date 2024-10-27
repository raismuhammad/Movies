package com.rais.movies.data.local

import com.rais.movies.data.local.dao.MovieNowPlayingDao
import com.rais.movies.data.local.dao.MovieTopRatedDao
import com.rais.movies.data.local.entity.MoviesNowPlayingEntity
import com.rais.movies.data.local.entity.MoviesTopRatedEntity
import com.rais.movies.model.MovieTopRated
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(val dao: MovieTopRatedDao, val nowPlayingDao: MovieNowPlayingDao) {

    // top rated

    fun insertMovieTopRated(model: List<MoviesTopRatedEntity>) = dao.insertMovieTopRated(model)

    fun getMoviesTopRated() : Flow<List<MoviesTopRatedEntity>> = dao.getMoviesTopRated()

    // now playing

    fun insertMovieNowPlaying(model: List<MoviesNowPlayingEntity>) = nowPlayingDao.inserMovieNowPlaying(model)

    fun getMoviesNowPlaying() : Flow<List<MoviesNowPlayingEntity>> = nowPlayingDao.getMoviesNowPlaying()

    fun getFavoriteMoviesNowPlaying(): Flow<List<MoviesNowPlayingEntity>> = nowPlayingDao.getFavoriteMovies()

    fun getMovieByIdNowPlaying(id: Int?) : Flow<MoviesNowPlayingEntity> = nowPlayingDao.getMovieById(id)

    fun setFavoriteMoviesNowPlaying(movies: MoviesNowPlayingEntity, newState: Boolean) {
        movies.isLike = newState
        nowPlayingDao.updateFavoriteMovie(movies)
    }
}