package com.example.opgg.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.opgg.R
import com.example.opgg.databinding.ActivityMainBinding
import com.example.opgg.utils.view.WrapperLayoutManager
import com.example.opgg.viewmodels.*
import com.example.opgg.views.adapters.MainListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: MainListAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initObserver()
        initBinding()
        initLayout()

        viewModel.refresh()
    }

    private fun initObserver() {
        viewModel.mainListState.observe(this) { state ->
            when(state){
                is RefreshLayoutState -> {
                    listAdapter.clear()
                    viewModel.getSummoner()
                }
                is AddSummonerLayoutState -> listAdapter.add(state.data)
                is UpsertSummaryLayoutState -> {
                    listAdapter.upsertSummary(
                        state.summary,
                        state.champions,
                        state.positions,
                        state.games
                    )
                    listAdapter.addAll(state.games)
                }
            }
        }
    }

    private fun initBinding(){
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initLayout(){
        listAdapter = MainListAdapter(
            this,
            viewModel
        ){
            viewModel.getGames()
        }
        binding.layoutList.run {
            layoutManager = WrapperLayoutManager(context, RecyclerView.VERTICAL)
            adapter = listAdapter
        }
    }
}