package com.rais.movies.di

import android.content.Context
import androidx.room.Room
import com.rais.movies.data.local.dao.MovieNowPlayingDao
import com.rais.movies.data.local.dao.MovieTopRatedDao
import com.rais.movies.data.local.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app, MoviesDatabase::class.java, "movies_database"
    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideMoviesTopRatedDao(db: MoviesDatabase): MovieTopRatedDao = db.getMoviesTopRated()

    @Provides
    @Singleton
    fun provideMoviesNowPlayingDao(db: MoviesDatabase): MovieNowPlayingDao = db.getMoviesNowPlaying()
}