package com.challenge.cstvapp.data.source

import com.challenge.cstvapp.extensions.getOrThrowDomainError
import com.challenge.cstvapp.extensions.toDomainError
import com.challenge.cstvapp.extensions.toLocalDateTime
import com.challenge.cstvapp.model.domain.MatchDetailDomain
import com.challenge.cstvapp.model.domain.MatchDomain
import com.challenge.cstvapp.model.mapper.toDomain
import com.challenge.cstvapp.services.ApiServices
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

class MatchDataSourceRemote @Inject constructor(
    private val api: ApiServices
): MatchDataSource {

    override suspend fun getMatchList(): List<MatchDomain> {
        return runCatching {
            api.getMatchesList(
                currentDate = getCurrentDate(),
                size = 50
            )
        }.getOrThrowDomainError().toDomain()
    }

    override suspend fun getMatchDetail(matchId: Long): MatchDetailDomain {
        return runCatching {
            api.getOpponents(
                matchId = matchId
            )
        }.getOrThrowDomainError().toDomain()
    }

    private fun getCurrentDate(): LocalDateTime = Date().toLocalDateTime()
}