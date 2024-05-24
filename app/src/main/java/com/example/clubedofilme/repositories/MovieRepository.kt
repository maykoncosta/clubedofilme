package com.example.clubedofilme.repositories

import android.util.Log
import com.example.clubedofilme.api.ApiClient
import com.example.clubedofilme.api.MovieService
import com.example.clubedofilme.models.Actor
import com.example.clubedofilme.models.ActorResponse
import com.example.clubedofilme.models.Movie
import com.example.clubedofilme.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val movieService: MovieService = ApiClient.instance.create(MovieService::class.java)
    private val apiKey = "d64e12a20a79b368c0a6af27f9a7e1ad"

    fun fetchPopularMovies(page: Int, callback: (List<Movie>?) -> Unit) {
        movieService.getPopularMovies(apiKey, page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.movies)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun fetchPopularActors(page: Int, callback: (List<Actor>?) -> Unit) {
        movieService.getPopularActors(apiKey, page).enqueue(object : Callback<ActorResponse> {
            override fun onResponse(call: Call<ActorResponse>, response: Response<ActorResponse>) {
                if (response.isSuccessful) {
                    Log.v("MovieRepository", "fetchPopularActors: ${response.body()?.actors}")
                    callback(response.body()?.actors)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<ActorResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun searchMovies(query: String, page: Int, callback: (List<Movie>?) -> Unit) {
        movieService.searchMovies(apiKey, query, page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.movies)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun searchActors(query: String, page: Int, callback: (List<Actor>?) -> Unit) {
        movieService.searchActors(apiKey, query, page).enqueue(object : Callback<ActorResponse> {
            override fun onResponse(call: Call<ActorResponse>, response: Response<ActorResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.actors)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<ActorResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

}
