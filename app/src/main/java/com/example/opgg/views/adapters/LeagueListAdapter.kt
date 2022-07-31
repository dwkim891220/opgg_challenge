package com.example.opgg.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.example.opgg.databinding.ILeagueBinding
import com.example.opgg.models.client.CLeague
import com.example.opgg.utils.view.BaseListAdapter
import com.example.opgg.utils.view.DataBoundViewHolder

class LeagueListAdapter(
    private val lifecycleOwner: LifecycleOwner,
): BaseListAdapter<CLeague>() {
    override fun createBinding(parent: ViewGroup, viewType: Int): DataBoundViewHolder =
        LeagueViewHolder(
            lifecycleOwner,
            ILeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )


    override fun bind(holder: DataBoundViewHolder, position: Int) {
        when (holder) {
            is LeagueViewHolder -> holder.bind(dataList[position])
            else -> throw RuntimeException()
        }
    }

    inner class LeagueViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val binding: ILeagueBinding,
    ): DataBoundViewHolder(binding) {
        fun bind(data: CLeague?) {
            data?.run {
                binding.lifecycleOwner = lifecycleOwner
                binding.data = this
            }
        }
    }
}