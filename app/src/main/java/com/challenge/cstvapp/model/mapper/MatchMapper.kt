package com.challenge.cstvapp.model.mapper

import com.challenge.cstvapp.extensions.getStatusLabel
import com.challenge.cstvapp.extensions.toLocalDateTime
import com.challenge.cstvapp.model.domain.MatchDomain
import com.challenge.cstvapp.model.domain.Opponent
import com.challenge.cstvapp.model.domain.StatusEnum
import com.challenge.cstvapp.model.remote.MatchRemote
import com.challenge.cstvapp.model.remote.Team

internal fun List<MatchRemote>.toDomain() = map { it.toDomain() }

fun MatchRemote.toDomain(): MatchDomain {
    return MatchDomain(
        id = id,
        leagueImage = league.imageUrl,
        leagueName = "${league.name} - ${serie.name}",
        scheduleAt = scheduleAt.toLocalDateTime(),
        status = StatusEnum.parseFrom(status),
        opponents = opponents.toOpponentsDomain(),
        order = StatusEnum.getOrder(status)
    )
}

private fun List<Team>.toOpponentsDomain() : List<Opponent> {
    return if(this.isNotEmpty()) {
        val opponents = mutableListOf<Opponent>()
        this.forEach {
            val opponent = Opponent(it.opponent.name, it.opponent.imageUrl ?: "")
            opponents.add(opponent)
        }
        opponents
    } else {
        mutableListOf(Opponent.empty(1), Opponent.empty(2))
    }
}

fun List<MatchDomain>.sortedAndFilter() =
    this.sortedBy { it.scheduleAt }
        .sortedBy { it.order }
        .filter { it.status == StatusEnum.NOT_STARTED ||
                it.status == StatusEnum.RUNNING ||
                it.status == StatusEnum.FINISHED
        }

fun MatchDomain.getStatus(): String {
    return when(this.status) {
        StatusEnum.RUNNING -> "AGORA"
        StatusEnum.FINISHED -> "FINALIZADA"
        else -> this.scheduleAt.getStatusLabel()
    }
}