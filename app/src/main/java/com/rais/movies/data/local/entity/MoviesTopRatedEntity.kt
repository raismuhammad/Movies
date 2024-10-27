package com.rais.movies.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import javax.annotation.Nonnull

@Parcelize
@Entity(tableName = "movies_top_rated_table")
data class MoviesTopRatedEntity(
    @PrimaryKey
    @Nonnull
    var idMovie: Int,
    var title: String? = null,
    var original_title: String? = null,
    var poster_path: String? = null,
    var backdrop_path: String? = null,
    var release_date: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var vote_average: Double? = null,
    var isLike: Boolean = false
): Parcelable
