package com.example.opgg.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.opgg.models.client.CGame
import com.example.opgg.utils.view.ListLayoutState
import com.example.opgg.utils.viewmodel.ViewModelState

class MainViewModel {
    val matches = MutableLiveData<List<CGame>>()
    val matchesState = MutableLiveData<ViewModelState>()
    val matchesLoading = MutableLiveData<Boolean>()
    val matchesLayoutState = MutableLiveData(ListLayoutState.List)

    fun refresh(){

    }

    fun getSummoner(){

    }

    fun getMatches(){

    }
}