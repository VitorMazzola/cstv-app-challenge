package com.challenge.cstvapp.services

import com.challenge.cstvapp.model.remote.MatchDetailRemote
import com.challenge.cstvapp.model.remote.MatchRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDateTime

interface ApiServices {
    @GET("csgo/matches")
    suspend fun getMatchesList(
        @Query("begin_at") currentDate: LocalDateTime,
        @Query("size") size: Int
    ): List<MatchRemote>

    @GET("matches/{matchId}/opponents")
    suspend fun getOpponents(
        @Path("matchId") matchId: Long
    ): MatchDetailRemote
}