package com.example.opgg.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.opgg.models.client.CGame
import com.example.opgg.models.client.CSummoner
import com.example.opgg.repositories.IRepository
import com.example.opgg.utils.viewmodel.RxViewModel
import com.example.opgg.utils.viewmodel.ScheduleProvider
import com.example.opgg.utils.viewmodel.ViewModelState
import com.example.opgg.utils.viewmodel.with
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: IRepository,
    private val scheduleProvider: ScheduleProvider,
): RxViewModel() {
    val mainListState = MutableLiveData<ViewModelState>()
    var hasMoreList: Boolean = true

    private var lastMatches: Long? = null

    fun refresh(){
        mainListState.value = RefreshLayoutState
        lastMatches = null
        hasMoreList = true
    }

    fun getSummoner(){
        repo.getSummoner()
            .with(scheduleProvider)
            .subscribe(
                { result ->
                    mainListState.value = AddSummonerLayoutState(CSummoner(result.summoner))
                    getGames()
                },
                { throwable ->
                    // TODO
                }
            )
    }

    fun getGames(){
        repo.getMatches(lastMatches)
            .with(scheduleProvider)
            .subscribe(
                { result ->
                    val games = result.games?.map { game -> CGame(game) } ?: emptyList()

                    if(games.isNotEmpty()){
                        lastMatches = games.last().createDate
                        mainListState.value = AddGameListLayoutState(games)
                    }

                    hasMoreList = lastMatches != null && games.size == 20
                },
                { throwable ->
                    // TODO
                }
            )
    }
}

object RefreshLayoutState : ViewModelState()
data class AddSummonerLayoutState(val data: CSummoner) : ViewModelState()
data class AddGameListLayoutState(val data: List<CGame>) : ViewModelState()