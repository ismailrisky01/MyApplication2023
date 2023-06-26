package com.ismaildev.myapplication2023.data.retrofit


import com.ismaildev.myapplication2023.data.model.MovieResponse
import retrofit2.http.*

interface Endpoint {
    @GET("movies")
    suspend fun getMovie(
    ): MovieResponse

}