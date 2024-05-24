package com.example.clubedofilme.models

data class Group(
    val groupId: String = "",
    val groupName: String = "",
    val members: List<String> = listOf(),
    val combinedPreferences: CombinedPreferences = CombinedPreferences()
)

data class CombinedPreferences(
    val favoriteMovies: List<String> = listOf(),
    val favoriteGenres: List<String> = listOf(),
    val favoriteActors: List<String> = listOf()
)
