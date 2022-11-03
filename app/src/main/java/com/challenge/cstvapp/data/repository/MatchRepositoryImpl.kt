package com.challenge.cstvapp.data.repository

import com.challenge.cstvapp.data.source.MatchDataSource
import com.challenge.cstvapp.model.domain.MatchDetailDomain
import com.challenge.cstvapp.model.domain.MatchDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val remoteDataSource: MatchDataSource
): MatchRepository {
    override suspend fun getMatches(): Flow<List<MatchDomain>> {
        return flow { emit(remoteDataSource.getMatchList()) }
    }

    override suspend fun getMatchDetail(matchId: Long): Flow<MatchDetailDomain> {
        return flow { emit(remoteDataSource.getMatchDetail(matchId)) }
    }
}