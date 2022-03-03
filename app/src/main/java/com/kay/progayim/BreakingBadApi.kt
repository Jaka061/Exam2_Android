package com.kay.progayim

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BreakingBadApi {

    @GET("/api/character")
    fun getAll(): Observable<RepoResult>

    @GET("/api/character/{id}")
    fun getEpisode(@Path("id") episode_id: Long): Single<List<Character>>
}
