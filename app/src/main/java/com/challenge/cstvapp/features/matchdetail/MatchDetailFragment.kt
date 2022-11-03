package com.challenge.cstvapp.features.matchdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.cstvapp.R
import com.challenge.cstvapp.databinding.FragmentMatchDetailBinding
import com.challenge.cstvapp.extensions.getMatchDomain
import com.challenge.cstvapp.extensions.loadTeamsLogo
import com.challenge.cstvapp.extensions.observe
import com.challenge.cstvapp.features.matches.MatchUiAction
import com.challenge.cstvapp.model.domain.MatchDetailDomain
import com.challenge.cstvapp.model.domain.MatchDomain
import com.challenge.cstvapp.model.mapper.getStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailFragment : Fragment() {
    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: FragmentMatchDetailBinding

    private var matchDomain: MatchDomain? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_match_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        observers()

        arguments?.getMatchDomain(ARG_MATCH_DOMAIN)?.let {
            configureMatch(it)
        }
    }

    private fun initToolbar() {
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbar, navHostFragment)
        navHostFragment.addOnDestinationChangedListener { _, _, _ ->
            context?.let { ctx ->
                binding.toolbar.navigationIcon = ContextCompat.getDrawable(ctx, R.drawable.ic_arrow_left)
            }
        }
    }

    private fun configureMatch(match: MatchDomain) {
        binding.apply {
            teamOne = match.opponents?.first()?.name
            teamTwo = match.opponents?.last()?.name
            title = match.leagueName
            dateHour = match.getStatus()
        }
        match.opponents?.first()?.imageUrl?.loadTeamsLogo(requireContext(), binding.ivTeamOne)
        match.opponents?.last()?.imageUrl?.loadTeamsLogo(requireContext(), binding.ivTeamTwo)

        viewModel.getMatchDetail(match.id)
    }

    private fun observers() {
        viewModel.action.observe(this) { action ->
            when(action) {
                is MatchUiAction.MatchesNotFound -> binding.tvEmpty.visibility = View.VISIBLE
                is MatchUiAction.Unexpected -> {}
                is MatchUiAction.Loading -> binding.progressBar.isVisible = action.isLoading
            }
        }

        viewModel.state.observe(this) {
            it.matchDetail?.let { detail ->
                configurePlayers(detail)
            }
        }
    }

    private fun configurePlayers(matchDetail: MatchDetailDomain) {
        if(matchDetail.players.isNotEmpty()) {
            val adapter = PlayerAdapter(requireContext(), matchDetail.players)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        } else {
            binding.tvEmpty.visibility = View.VISIBLE
        }
    }

    companion object {
        const val ARG_MATCH_DOMAIN = "matchDomainArgs"

        fun arguments(match: MatchDomain) = Bundle().apply {
            putSerializable(ARG_MATCH_DOMAIN, match)
        }
    }
}