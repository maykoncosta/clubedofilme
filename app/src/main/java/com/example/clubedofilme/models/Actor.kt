package com.example.clubedofilme.models

import com.google.gson.annotations.SerializedName

data class Actor(
    val id: Int,
    @SerializedName("profile_path") val profilePath: String,
    val name: String
)
