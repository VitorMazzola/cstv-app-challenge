package com.challenge.cstvapp.features.matches

import androidx.lifecycle.viewModelScope
import com.challenge.cstvapp.core.BaseViewModel
import com.challenge.cstvapp.extensions.NotFoundException
import com.challenge.cstvapp.extensions.UnexpectedException
import com.challenge.cstvapp.model.domain.MatchDomain
import com.challenge.cstvapp.model.mapper.sortedAndFilter
import com.challenge.cstvapp.usecase.GetMachesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val getMachesUseCase: GetMachesUseCase
) : BaseViewModel<MatchUiState, MatchUiAction>(MatchUiState()) {

    fun fetchMatches() = viewModelScope.launch {
        getMachesUseCase()
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
            }.collect { matches ->
                setState {
                    copy(matches = matches.sortedAndFilter())
                }
            }
    }

}

data class MatchUiState(
    val matches: List<MatchDomain> = emptyList()
)

sealed interface MatchUiAction {
    object Unexpected: MatchUiAction
    data class Loading(val isLoading: Boolean): MatchUiAction
    data class MatchesNotFound(val message: String) : MatchUiAction
}