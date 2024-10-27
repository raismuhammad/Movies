package com.rais.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rais.movies.data.local.entity.MoviesNowPlayingEntity
import com.rais.movies.data.local.entity.MoviesTopRatedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieTopRatedDao {
    @Insert
    fun insertMovieTopRated(movies: List<MoviesTopRatedEntity>)

    @Query("SELECT * FROM movies_top_rated_table")
    fun getMoviesTopRated(): Flow<List<MoviesTopRatedEntity>>
}