package com.challenge.cstvapp.usecase

import com.challenge.cstvapp.data.repository.MatchRepository
import com.challenge.cstvapp.model.domain.MatchDetailDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatchDetailUseCase@Inject constructor(
    private val repository: MatchRepository
) {
    suspend operator fun invoke(matchId: Long): Flow<MatchDetailDomain> {
        return repository.getMatchDetail(matchId)
    }
}