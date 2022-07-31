package com.example.opgg.utils.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T>(
    private val infiniteScrollListener: (() -> Unit)? = null
) : RecyclerView.Adapter<DataBoundViewHolder>() {
    val dataList: ArrayList<T> = arrayListOf()

    fun addFirst(item: T){
        dataList.add(0, item)
        notifyItemInserted(0)
    }

    fun add(item: T){
        dataList.add(item)
        notifyItemInserted(itemCount)
    }

    fun addAll(itemList: List<T>) {
        dataList.addAll(itemList)
        notifyItemRangeChanged(itemCount, itemList.size)
    }

    fun clearAddAll(itemList: List<T>){
        dataList.clear()
        dataList.addAll(itemList)
        notifyItemRangeChanged(0, itemList.size)
    }

    fun clear() {
        val itemCount = dataList.size
        dataList.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    fun getItem(position: Int) = dataList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val viewHolder = createBinding(parent, viewType)
        viewHolder.created()

        return viewHolder
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): DataBoundViewHolder

    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        if(position == itemCount - 3 ){
            infiniteScrollListener?.invoke()
        }

        bind(holder, position)
        holder.getDataBinding().executePendingBindings()
    }

    protected abstract fun bind(holder: DataBoundViewHolder, position: Int)

    override fun onViewAttachedToWindow(holder: DataBoundViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.attached()
    }

    override fun onViewDetachedFromWindow(holder: DataBoundViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.detached()
    }

    override fun getItemCount(): Int = dataList.size
}