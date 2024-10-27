package com.rais.movies.data.local.database.utils

import com.rais.movies.data.local.entity.MoviesNowPlayingEntity
import kotlinx.coroutines.flow.Flow

object DataDummy {

    fun generateDummyMoviesEntity(): ArrayList<MoviesNowPlayingEntity> {
        val newMovies = ArrayList<MoviesNowPlayingEntity>()
        for (i in 1..10) {
            val movie = MoviesNowPlayingEntity(
                123,
                "Tendangan si Madun",
                "Tendangan Madun",
                "https://upload.wikimedia.org/wikipedia/id/1/15/MD_-_Tendangan_si_Madun_2_A.jpg",
                "https://i.ytimg.com/vi/qfxklczxMJg/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLAC3zZA7pVj-nBKTFIl4shlZeCwpg",
                "2024-08-08",
                "tendangan madun adalah",
                222.222,
                12.3321,
                true
            )
            newMovies.add(movie)
        }
        return newMovies
    }

    fun generateDummyMoviesEntity2(): MoviesNowPlayingEntity {
        val movie = MoviesNowPlayingEntity(
            123,
            "Tendangan si Bambang",
            "Tendangan Madun",
            "https://upload.wikimedia.org/wikipedia/id/1/15/MD_-_Tendangan_si_Madun_2_A.jpg",
            "https://i.ytimg.com/vi/qfxklczxMJg/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLAC3zZA7pVj-nBKTFIl4shlZeCwpg",
            "2024-08-08",
            "tendangan madun adalah",
            222.222,
            12.3321,
            true
        )
        return movie
    }

    fun generateDummyMoviesEntity3(): MoviesNowPlayingEntity {
        val movie = MoviesNowPlayingEntity(
            231,
            "Tendangan si Jambang",
            "Tendangan Jambang",
            "https://upload.wikimedia.org/wikipedia/id/1/15/MD_-_Tendangan_si_Madun_2_A.jpg",
            "https://i.ytimg.com/vi/qfxklczxMJg/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLAC3zZA7pVj-nBKTFIl4shlZeCwpg",
            "2024-08-08",
            "tendangan madun adalah",
            222.222,
            12.3321,
            true
        )
        return movie
    }
}