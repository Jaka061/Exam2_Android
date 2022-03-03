package com.kay.progayim

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RickandmortyApi {

    @GET("/api/character")
    fun getAll(): Observable<RepoResult>

    @GET("/api/character/{id}")
    fun getById(@Path("id") id: Long): Single<Character>
}
