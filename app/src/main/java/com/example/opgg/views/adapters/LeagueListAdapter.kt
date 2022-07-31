package com.example.opgg.views.adapters

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.opgg.databinding.ILeagueBinding
import com.example.opgg.models.client.CLeague
import com.example.opgg.utils.view.BaseListAdapter
import com.example.opgg.utils.view.DataBoundViewHolder
import com.example.opgg.utils.view.toPixel

class LeagueListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    data: List<CLeague>
): BaseListAdapter<CLeague>() {
    init {
        addAll(data)
    }

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

class LeagueListDecoration(context: Context?) : RecyclerView.ItemDecoration() {
    private val padding = 8.toPixel(context)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = padding
    }
}