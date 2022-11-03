package com.challenge.cstvapp.data.repository

import com.challenge.cstvapp.model.domain.MatchDetailDomain
import com.challenge.cstvapp.model.domain.MatchDomain
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    suspend fun getMatches(): Flow<List<MatchDomain>>
    suspend fun getMatchDetail(matchId: Long): Flow<MatchDetailDomain>
}