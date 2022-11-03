package com.challenge.cstvapp.features.matches

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.challenge.cstvapp.R
import com.challenge.cstvapp.databinding.ItemMatchListBinding
import com.challenge.cstvapp.extensions.loadTeamsLogo
import com.challenge.cstvapp.model.domain.MatchDomain
import com.challenge.cstvapp.model.domain.StatusEnum
import com.challenge.cstvapp.model.mapper.getStatus

class MatchAdapter(
    private val context: Context,
    private val matches: List<MatchDomain>,
    private val listener: MatchListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemMatchListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_match_list,
            parent,
            false
        )
        return MatchesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as MatchesViewHolder
        viewHolder.bind(matches[position], listener)
    }

    override fun getItemCount() = matches.size
}

class MatchesViewHolder(
    private val binding: ItemMatchListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(matchDomain: MatchDomain, listener: MatchListener) = with(itemView){
        binding.apply {
            teamOne = matchDomain.opponents?.first()?.name
            teamTwo = matchDomain.opponents?.last()?.name

            leagueName = matchDomain.leagueName

            dateHour = matchDomain.getStatus()
        }

        matchDomain.opponents?.first()?.imageUrl?.loadTeamsLogo(context, binding.ivTeamOne)
        matchDomain.opponents?.last()?.imageUrl?.loadTeamsLogo(context, binding.ivTeamTwo)
        matchDomain.leagueImage?.loadTeamsLogo(context, binding.ivLeague)

        if(matchDomain.status == StatusEnum.RUNNING) {
            binding.containerStatus.setBackgroundResource(R.drawable.rounded_status_red)
        } else {
            binding.containerStatus.setBackgroundResource(R.drawable.rounded_status_gray)
        }

        binding.cardMatch.setOnClickListener { listener.onMatchClick(matchDomain) }
    }
}

interface MatchListener {
    fun onMatchClick(match: MatchDomain) {}
}