package com.challenge.cstvapp.model.domain

import java.io.Serializable
import java.time.LocalDateTime

typealias MatchDomain = Match

data class Match(
    val id: Long,
    val leagueImage: String?,
    val leagueName: String, // League name + serie name
    val scheduleAt: LocalDateTime,
    val status: StatusEnum,
    val opponents: List<Opponent>?,
    val order: Int
): Serializable

data class Opponent(
    val name: String,
    val imageUrl: String?
) : Serializable {
    companion object {
        fun empty(team: Int) = Opponent("Time $team", "")
    }
}

enum class StatusEnum(val status: String?):  Serializable {
    NOT_STARTED("not_started"),
    RUNNING("running"),
    FINISHED("finished"),
    UNKNOWN(null);

    companion object {
        fun parseFrom(status: String?) =
            values().firstOrNull { it.status == status } ?: UNKNOWN

        fun getOrder(status: String) : Int {
            return when(parseFrom(status)) {
                RUNNING -> 1
                NOT_STARTED -> 2
                FINISHED -> 3
                else -> 4
            }
        }
    }
}