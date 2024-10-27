package com.rais.movies.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rais.movies.data.local.dao.MovieNowPlayingDao
import com.rais.movies.data.local.dao.MovieTopRatedDao
import com.rais.movies.data.local.entity.MoviesNowPlayingEntity
import com.rais.movies.data.local.entity.MoviesTopRatedEntity

@Database(
    entities = [MoviesTopRatedEntity::class, MoviesNowPlayingEntity::class],
    version = 6
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesTopRated() : MovieTopRatedDao
    abstract fun getMoviesNowPlaying() : MovieNowPlayingDao
}