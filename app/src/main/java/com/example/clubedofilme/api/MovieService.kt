package com.example.clubedofilme.api

import com.example.clubedofilme.models.ActorResponse
import com.example.clubedofilme.models.GenreResponse
import com.example.clubedofilme.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String = "pt-BR"
    ): Call<MovieResponse>

    @GET("person/popular")
    fun getPopularActors(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String = "pt-BR"
    ): Call<ActorResponse>

    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String = "pt-BR",
    ): Call<MovieResponse>

    @GET("search/person")
    fun searchActors(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String = "pt-BR",
    ): Call<ActorResponse>

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): Call<GenreResponse>
}
