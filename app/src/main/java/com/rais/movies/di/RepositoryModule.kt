package com.rais.movies.di

import com.rais.movies.data.local.LocalDataSource
import com.rais.movies.data.remote.source.RemoteDataSource
import com.rais.movies.data.repository.MovieNowPlayingRepository
import com.rais.movies.data.repository.MovieTopRatedRepository
import com.rais.movies.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieTopRatedRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutor: AppExecutors) : MovieTopRatedRepository {
        return MovieTopRatedRepository(remoteDataSource, localDataSource, appExecutor)
    }

    @Provides
    @Singleton
    fun provideMovieNowPlayingRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutor: AppExecutors) : MovieNowPlayingRepository {
        return MovieNowPlayingRepository(remoteDataSource, localDataSource, appExecutor)
    }

    @Provides
    @Singleton
    fun provideAppExecutors() = AppExecutors()
}