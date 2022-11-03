package com.challenge.cstvapp.model.mapper

import com.challenge.cstvapp.model.domain.MatchDetailDomain
import com.challenge.cstvapp.model.domain.PlayerDomain
import com.challenge.cstvapp.model.remote.MatchDetailRemote
import com.challenge.cstvapp.model.remote.OpponentDetail

fun MatchDetailRemote.toDomain(): MatchDetailDomain {
    return MatchDetailDomain(
        players = (opponents.getFirstPlayers() + opponents.getLastPlayers()).sortedBy { it.order }
    )
}

fun List<OpponentDetail>.getFirstPlayers() : List<PlayerDomain> {
    val players = mutableListOf<PlayerDomain>()
    return if(this.isNotEmpty()) {
        var order = 1
        this.first().players.forEach {
            val player = PlayerDomain(it.slug, it.imageUrl, it.name, order)
            players.add(player)
            order += 2
        }
        players
    } else {
        players
    }
}

fun List<OpponentDetail>.getLastPlayers() : List<PlayerDomain> {
    val players = mutableListOf<PlayerDomain>()
    return if(this.isNotEmpty()) {
        var order = 2
        this.last().players.forEach {
            val player = PlayerDomain(it.slug, it.imageUrl, it.name, order)
            players.add(player)
            order += 2
        }
        players
    } else {
        players
    }
}