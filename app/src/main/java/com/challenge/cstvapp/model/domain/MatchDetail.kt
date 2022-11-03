package com.challenge.cstvapp.model.domain

import java.io.Serializable

internal typealias MatchDetailDomain = MatchDetail

data class MatchDetail(
    val players: List<Player>
): Serializable

internal typealias PlayerDomain = Player
data class Player(
    val nickName: String,
    val profilePicture: String?,
    val fullName: String,
    val order: Int,
    val isLeft: Boolean = (order % 2 == 0)
) {
    companion object {
        fun empty() = Player("", "", "", 0)
    }
}