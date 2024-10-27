package com.rais.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rais.movies.data.local.entity.MoviesNowPlayingEntity
import com.rais.movies.data.local.entity.MoviesTopRatedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieNowPlayingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserMovieNowPlaying(movies: List<MoviesNowPlayingEntity>)

    @Query("SELECT * FROM movies_now_playing_table")
    fun getMoviesNowPlaying(): Flow<List<MoviesNowPlayingEntity>>

    @Query("SELECT * FROM movies_now_playing_table WHERE isLike = 1")
    fun getFavoriteMovies(): Flow<List<MoviesNowPlayingEntity>>

    @Update
    fun updateFavoriteMovie(movie: MoviesNowPlayingEntity)

    @Query("SELECT * FROM movies_now_playing_table WHERE idMovie=:id")
    fun getMovieById(id: Int?): Flow<MoviesNowPlayingEntity>
}