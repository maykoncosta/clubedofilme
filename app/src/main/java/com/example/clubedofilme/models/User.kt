package com.example.clubedofilme.models

data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val favoriteMovies: List<String> = listOf(),
    val favoriteGenres: List<String> = listOf(),
    val favoriteActors: List<String> = listOf()
)