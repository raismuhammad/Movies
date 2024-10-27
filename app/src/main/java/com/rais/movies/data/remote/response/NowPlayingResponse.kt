package com.rais.movies.data.remote.response

import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(

    @SerializedName("dates")
    var dates: Dates? = null,

    @SerializedName("page")
    var page: Int? = null,

    @SerializedName("results")
    var results: List<NowPlayingResult>? = null,
)

data class NowPlayingResult(

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

//    @SerializedName("genre_ids")
//    var genre_ids: ArrayList<Int>? = null,

    @SerializedName("id")
    var id: Int,

    @SerializedName("original_language")
    var original_language: String? = null,

    @SerializedName("original_title")
    var original_title: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("release_date")
    var release_date: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("video")
    var video: String? = null,

    @SerializedName("vote_average")
    var vote_average: Double? = null,

    @SerializedName("vote_count")
    var vote_count: Int? = null,

)

data class Dates(

    @SerializedName("maximum")
    var maximum: String? = null,

    @SerializedName("minimum")
    var minimum: String? = null,
)
