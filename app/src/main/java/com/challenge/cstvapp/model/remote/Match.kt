package com.challenge.cstvapp.model.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

internal typealias MatchRemote = Match
data class Match(
    @SerializedName("id") val id: Long,
    @SerializedName("league") val league: League,
    @SerializedName("serie") val serie: Serie,
    @SerializedName("opponents") val opponents: List<Team>,
    @SerializedName("status") val status: String,
    @SerializedName("scheduled_at") val scheduleAt: Date
) : Serializable

data class League(
    @SerializedName("id") val id: Long,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("name") val name: String
): Serializable

data class Team(
    @SerializedName("opponent") val opponent: Opponent,
    @SerializedName("type") val type: String
): Serializable

data class Opponent(
    @SerializedName("id") val id: Long,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("name") val name: String
) : Serializable

data class Serie(
    @SerializedName("id") val id: Long,
    @SerializedName("full_name") val name: String
): Serializable
