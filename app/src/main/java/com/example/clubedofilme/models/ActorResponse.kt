package com.example.clubedofilme.models

import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("results") val actors: List<Actor>
)
