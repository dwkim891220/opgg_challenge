package com.example.opgg.repositories

import com.example.opgg.repositories.results.GetMatchesResult
import com.example.opgg.repositories.results.GetSummonerResult
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val apiService: OPGGApiService
): IRepository {
    override fun getSummoner(): Single<GetSummonerResult> = apiService.getSummoner()
    override fun getMatches(): Single<GetMatchesResult> = apiService.getMatches()
}