package com.ismaildev.myapplication2023.data.model

data class MovieResponse(
	val movieResponse: List<MovieResponseItem?>? = null
)

data class MovieResponseItem(
	val image: String? = null,
	val movie: String? = null,
	val rating: Double? = null,
	val id: Int? = null,
	val imdbUrl: String? = null
)

