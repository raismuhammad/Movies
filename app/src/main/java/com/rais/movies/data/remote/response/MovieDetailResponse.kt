package com.rais.movies.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("page")
    var adult: Boolean? = null,

    @SerializedName("results")
    var backdrop_path: String? = null,

    @SerializedName("results")
    var belongs_to_collection: BelongToCollection? = null,

    @SerializedName("results")
    var budget: String? = null,

    @SerializedName("results")
    var genres: ArrayList<GenresResult>? = null,

    @SerializedName("results")
    var homepage: String? = null,

    @SerializedName("results")
    var id: String? = null,

    @SerializedName("results")
    var imdb_id: String? = null,

    @SerializedName("results")
    var origin_country: List<String>? = null,

    @SerializedName("results")
    var original_language: String? = null,

    @SerializedName("results")
    var original_title: String? = null,

    @SerializedName("results")
    var overview: String? = null,

    @SerializedName("results")
    var popularity: String? = null,

    @SerializedName("results")
    var poster_path: String? = null,

    @SerializedName("results")
    var production_companies: ArrayList<ProductionCompaniesResult>? = null,

    @SerializedName("results")
    var production_countries: ArrayList<ProductionCountriesResult>? = null,

    @SerializedName("results")
    var release_date: String? = null,

    @SerializedName("results")
    var revenue: Int? = null,

    @SerializedName("results")
    var runtime: Int? = null,

    @SerializedName("results")
    var spoken_languages: ArrayList<SponkenLanguageResult>? = null,

    @SerializedName("results")
    var status: String? = null,

    @SerializedName("results")
    var tagline: String? = null,

    @SerializedName("results")
    var title: String? = null,

    @SerializedName("results")
    var video: Boolean? = null,

    @SerializedName("results")
    var vote_average: Double? = null,

    @SerializedName("results")
    var vote_count: Int? = null,
)

data class GenresResult(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("id")
    var name: String? = null,


)

data class BelongToCollection(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: Int? = null,

    @SerializedName("poster_path")
    var poster_path: Int? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: Int? = null,

)

data class ProductionCompaniesResult(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("logo_path")
    var logo_path: Int? = null,

    @SerializedName("name")
    var name: Int? = null,

    @SerializedName("origin_country")
    var origin_country: String? = null,
)

data class ProductionCountriesResult(

    @SerializedName("iso_3166_1")
    var iso_3166_1: Int? = null,

    @SerializedName("name")
    var name: Int? = null,
)

data class SponkenLanguageResult(

    @SerializedName("english_name")
    var english_name: String? = null,

    @SerializedName("iso_639_1")
    var iso_639_1: String? = null,

    @SerializedName("name")
    var name: String? = null,
)
