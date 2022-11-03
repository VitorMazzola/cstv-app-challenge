package com.challenge.cstvapp.data.source

import com.challenge.cstvapp.model.domain.MatchDetailDomain
import com.challenge.cstvapp.model.domain.MatchDomain

interface MatchDataSource {
    suspend fun getMatchList(): List<MatchDomain>
    suspend fun getMatchDetail(matchId: Long): MatchDetailDomain
}