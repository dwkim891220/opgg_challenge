package com.example.opgg.utils.view

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

open class DataBoundViewHolder(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)
    private val _binding: ViewDataBinding = binding

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    fun getDataBinding(): ViewDataBinding = _binding

    fun created(){
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    fun attached(){
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    fun detached(){
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}