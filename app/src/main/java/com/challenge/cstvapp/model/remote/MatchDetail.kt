package com.challenge.cstvapp.model.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

internal typealias MatchDetailRemote = MatchDetail

data class MatchDetail(
    @SerializedName("opponents") val opponents: List<OpponentDetail>
): Serializable

data class OpponentDetail(
    @SerializedName("players") val players: List<Player>
): Serializable

data class Player(
    @SerializedName("slug") val slug: String,
    @SerializedName("name") val name: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("image_url") val imageUrl: String?,
): Serializable

