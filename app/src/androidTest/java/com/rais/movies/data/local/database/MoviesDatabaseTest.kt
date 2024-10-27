package com.rais.movies.data.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.rais.movies.data.local.dao.MovieNowPlayingDao
import com.rais.movies.data.local.database.utils.DataDummy
import com.rais.movies.data.local.database.utils.getOrAwaitValue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesDatabaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MoviesDatabase
    private lateinit var dao: MovieNowPlayingDao
    private val sampleMovies = DataDummy.generateDummyMoviesEntity()
    private val sampleMovies2 = DataDummy.generateDummyMoviesEntity2()
    private val sampleMovies3 = DataDummy.generateDummyMoviesEntity3()

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MoviesDatabase::class.java
        ).build()
        dao = database.getMoviesNowPlaying()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun saveMovie_Success() = runTest {
        dao.inserMovieNowPlaying(sampleMovies)

        val moviesFlow = dao.getMoviesNowPlaying()
        val moviesList = moviesFlow.first()
        val getMovieById = dao.getMovieById(sampleMovies2.idMovie)

        Assert.assertEquals(1, moviesList.size)
        Assert.assertEquals(sampleMovies2.idMovie, sampleMovies[0].idMovie)
    }

    @Test
    fun updated_movies() = runTest {
        dao.inserMovieNowPlaying(sampleMovies)
        dao.updateFavoriteMovie(sampleMovies3)
        val actualNews = dao.getMovieById(sampleMovies3.idMovie)
        Assert.assertTrue(actualNews != null)
    }

}