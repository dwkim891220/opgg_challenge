package com.example.opgg.repositories

import com.example.opgg.constants.ApiUrl.SUMMONER
import com.example.opgg.constants.ApiUrl.MATCHES
import com.example.opgg.constants.Constants
import com.example.opgg.repositories.results.GetMatchesResult
import com.example.opgg.repositories.results.GetSummonerResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface OPGGApiService {
    @GET("$SUMMONER{summoner_name}")
    fun getSummoner(
        @Path("summoner_name") summonerName: String = Constants.SUMMONER_NAME
    ): Single<GetSummonerResult>

    @GET("$SUMMONER{summoner_name}/$MATCHES")
    fun getMatches(
        @Path("summoner_name") summonerName: String = Constants.SUMMONER_NAME,
        @Query("lastMatch") lastMatch: String?
    ): Single<GetMatchesResult>
}