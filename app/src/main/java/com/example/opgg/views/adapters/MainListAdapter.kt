package com.example.opgg.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.example.opgg.databinding.IGameBinding
import com.example.opgg.databinding.LSummaryBinding
import com.example.opgg.databinding.LSummonerBinding
import com.example.opgg.models.client.CGame
import com.example.opgg.models.client.CSummary
import com.example.opgg.models.client.CSummoner
import com.example.opgg.utils.view.BaseListAdapter
import com.example.opgg.utils.view.DataBoundViewHolder

class MainListAdapter(
    private val lifecycleOwner: LifecycleOwner,
): BaseListAdapter<IMainListItem>() {
    override fun getItemViewType(position: Int): Int =
        when(dataList[position]){
            is CSummoner -> IMainListItemType.Summoner.ordinal
            is CSummary -> IMainListItemType.Summary.ordinal
            is CGame -> IMainListItemType.Game.ordinal
            else -> throw RuntimeException()
        }

    override fun createBinding(parent: ViewGroup, viewType: Int): DataBoundViewHolder =
        when(viewType){
            IMainListItemType.Summoner.ordinal ->
                SummonerViewHolder(
                    lifecycleOwner,
                    LSummonerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                )
            IMainListItemType.Summary.ordinal ->
                SummaryViewHolder(
                    lifecycleOwner,
                    LSummaryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                )
            IMainListItemType.Game.ordinal ->
                GameViewHolder(
                    lifecycleOwner,
                    IGameBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                )
            else -> throw RuntimeException()
        }

    override fun bind(holder: DataBoundViewHolder, position: Int) {
        when(holder){
            is SummonerViewHolder -> holder.bind(dataList[position] as? CSummoner)
            is SummaryViewHolder -> holder.bind(dataList[position] as? CSummary)
            is GameViewHolder -> holder.bind(dataList[position] as? CGame)
            else -> throw RuntimeException()
        }
    }

    inner class SummonerViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val binding: LSummonerBinding,
    ): DataBoundViewHolder(binding) {
        fun bind(data: CSummoner?) {
            data?.run {
                binding.lifecycleOwner = lifecycleOwner
                binding.data = this
            }
        }
    }

    inner class SummaryViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val binding: LSummaryBinding,
    ): DataBoundViewHolder(binding) {
        fun bind(data: CSummary?) {
            data?.run {
                binding.lifecycleOwner = lifecycleOwner
                binding.data = this
            }
        }
    }

    inner class GameViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val binding: IGameBinding,
    ): DataBoundViewHolder(binding) {
        fun bind(data: CGame?) {
            data?.run {
                binding.lifecycleOwner = lifecycleOwner
                binding.data = this
            }
        }
    }
}

enum class IMainListItemType {
    Summoner,
    Summary,
    Game
}

interface IMainListItem