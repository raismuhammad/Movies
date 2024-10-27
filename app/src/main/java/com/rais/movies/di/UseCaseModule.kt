package com.rais.movies.di

import com.rais.movies.usecase.nowplaying.MovieNowPlayingInteractor
import com.rais.movies.usecase.nowplaying.MovieNowPlayingUseCase
import com.rais.movies.usecase.toprated.MovieTopRatedInteractor
import com.rais.movies.usecase.toprated.MovieTopRatedUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideTopRatedUseCase(impl : MovieTopRatedInteractor) : MovieTopRatedUseCase

    @Binds
    abstract fun provideNowPlayingUseCase(impl : MovieNowPlayingInteractor) : MovieNowPlayingUseCase
}