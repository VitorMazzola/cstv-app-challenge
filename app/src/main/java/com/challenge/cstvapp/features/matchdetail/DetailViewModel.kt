package com.challenge.cstvapp.features.matchdetail

import androidx.lifecycle.viewModelScope
import com.challenge.cstvapp.core.BaseViewModel
import com.challenge.cstvapp.extensions.NotFoundException
import com.challenge.cstvapp.extensions.UnexpectedException
import com.challenge.cstvapp.features.matches.MatchUiAction
import com.challenge.cstvapp.model.domain.MatchDetailDomain
import com.challenge.cstvapp.usecase.GetMatchDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMatchDetailUseCase: GetMatchDetailUseCase
) : BaseViewModel<MatchUiState, MatchUiAction>(MatchUiState()) {

    fun getMatchDetail(matchId: Long) = viewModelScope.launch {
        getMatchDetailUseCase(matchId)
            .flowOn(Dispatchers.Default)
            .onStart { sendAction(MatchUiAction.Loading(true)) }
            .onCompletion { sendAction(MatchUiAction.Loading(false)) }
            .flowOn(Dispatchers.Main)
            .catch {
                when(it) {
                    is NotFoundException ->
                        sendAction(MatchUiAction.MatchesNotFound(it.message ?: "Erro sem mensagem"))
                    is UnexpectedException ->
                        sendAction(MatchUiAction.Unexpected)
                    else -> sendAction(MatchUiAction.Unexpected)
                }
            }.collect { detail ->
                setState {
                    copy(matchDetail = detail)
                }
            }
    }
}

data class MatchUiState(
    val matchDetail: MatchDetailDomain? = null
)
