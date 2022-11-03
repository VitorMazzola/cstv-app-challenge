package com.challenge.cstvapp.features.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.cstvapp.R
import com.challenge.cstvapp.databinding.FragmentMatchListBinding
import com.challenge.cstvapp.extensions.observe
import com.challenge.cstvapp.features.matchdetail.MatchDetailFragment
import com.challenge.cstvapp.model.domain.MatchDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchListFragment : Fragment(), MatchListener {

    private val viewModel by viewModels<MatchViewModel>()

    private lateinit var binding: FragmentMatchListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_match_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()
        viewModel.fetchMatches()
    }

    private fun observers() {
        viewModel.action.observe(this) { action ->
            when(action) {
                is MatchUiAction.MatchesNotFound -> showError()
                is MatchUiAction.Unexpected -> showError()
                is MatchUiAction.Loading -> binding.progressBar.isVisible = action.isLoading
            }
        }

        viewModel.state.observe(this) { state ->
            configureMatches(state.matches)
        }
    }

    private fun configureMatches(matches: List<MatchDomain>) {
        val adapter = MatchAdapter(requireContext(), matches, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun showError() {
        binding.tvEmpty.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    override fun onMatchClick(match: MatchDomain) {
        val bundle = MatchDetailFragment.arguments(match)
        this.findNavController().navigate(R.id.action_matchListFragment_to_matchDetailFragment, bundle)
    }
}