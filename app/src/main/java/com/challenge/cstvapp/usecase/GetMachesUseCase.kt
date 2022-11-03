package com.challenge.cstvapp.usecase

import com.challenge.cstvapp.data.repository.MatchRepository
import com.challenge.cstvapp.model.domain.MatchDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMachesUseCase @Inject constructor(
    private val repository: MatchRepository
) {
    suspend operator fun invoke(): Flow<List<MatchDomain>> {
        return repository.getMatches()
    }
}