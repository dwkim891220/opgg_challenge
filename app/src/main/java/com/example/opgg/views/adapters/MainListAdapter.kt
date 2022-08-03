package com.example.opgg.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.opgg.databinding.IGameBinding
import com.example.opgg.databinding.LSummaryBinding
import com.example.opgg.databinding.LSummonerBinding
import com.example.opgg.models.client.*
import com.example.opgg.models.server.Summary
import com.example.opgg.utils.view.BaseListAdapter
import com.example.opgg.utils.view.DataBoundViewHolder
import com.example.opgg.utils.view.WrapperLayoutManager
import com.example.opgg.viewmodels.MainViewModel

class MainListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: MainViewModel,
    infiniteScrollListener: (() -> Unit),
): BaseListAdapter<IMainListItem>(infiniteScrollListener = infiniteScrollListener) {
    fun upsertSummary(
        summary: Summary?,
        champions: List<CChampion>,
        positions: List<CPosition>,
        games: List<CGame>,
    ){
        val existSummary = dataList.filterIsInstance<CSummary>().firstOrNull()

        if(existSummary != null){
            val summaryIndex = dataList.indexOf(existSummary)
            if(summaryIndex > -1){
                existSummary.update(
                    summary,
                    champions,
                    positions,
                    games
                )
                notifyItemChanged(summaryIndex)
            }
        }else{
            dataList.add(
                CSummary().apply {
                    update(
                        summary,
                        champions,
                        positions,
                        games
                    )
                }
            )
            notifyItemInserted(dataList.size)
        }
    }

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
            is SummonerViewHolder -> holder.bind(dataList[position] as? CSummoner, viewModel)
            is SummaryViewHolder -> holder.bind(dataList[position] as? CSummary)
            is GameViewHolder -> holder.bind(dataList[position] as? CGame)
            else -> throw RuntimeException()
        }
    }

    inner class SummonerViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val binding: LSummonerBinding,
    ): DataBoundViewHolder(binding) {
        fun bind(data: CSummoner?, viewModel: MainViewModel) {
            data?.run {
                binding.lifecycleOwner = lifecycleOwner
                binding.data = this
                binding.viewModel = viewModel
                initRecyclerView(this.leagues)
            }
        }

        private fun initRecyclerView(data: List<CLeague>){
            binding.rvLeagueList.run {
                layoutManager = WrapperLayoutManager(context, RecyclerView.HORIZONTAL)
                addItemDecoration(LeagueListDecoration(context))
                adapter = LeagueListAdapter(lifecycleOwner, data)
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