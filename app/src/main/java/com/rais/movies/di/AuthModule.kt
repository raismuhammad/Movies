package com.rais.movies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthToken(): String {
        return "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMzA2ZTViZDBmMTVkOGFjZTk1MDFkNTRjMmVmNzVlZSIsIm5iZiI6MTcyOTgyNDM5NS41MzkyOTgsInN1YiI6IjU4Yzc3NjExYzNhMzY4M2RjZDAwNmE3YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ERncp00VNnpPxKvE_svD3mNu23JGJEBr-rkwOf0A_CI"
    }
}
