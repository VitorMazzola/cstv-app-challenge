package com.challenge.cstvapp.features.matchdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.challenge.cstvapp.R
import com.challenge.cstvapp.databinding.ItemPlayerListBinding
import com.challenge.cstvapp.extensions.loadPicturePlayer
import com.challenge.cstvapp.model.domain.Player

class PlayerAdapter(
    private val context: Context,
    private val players: List<Player>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemPlayerListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_player_list,
            parent,
            false
        )
        return PlayersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as PlayersViewHolder
        viewHolder.bind(players[position])
    }

    override fun getItemCount() = players.size
}

class PlayersViewHolder(
    private val binding: ItemPlayerListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(player: Player) = with(itemView){

        if(!player.isLeft) {
            player.profilePicture?.loadPicturePlayer(context, binding.ivPlayerOne)
            binding.nickNamePlayerOne = player.nickName
            binding.namePlayerOne = player.fullName
        } else {
            player.profilePicture?.loadPicturePlayer(context, binding.ivPlayerTwo)
            binding.nickNamePlayerTwo = player.nickName
            binding.namePlayerTwo = player.fullName
        }

        binding.itemPlayerOne.isVisible = !player.isLeft
        binding.itemPlayerTwo.isVisible = player.isLeft


    }
}