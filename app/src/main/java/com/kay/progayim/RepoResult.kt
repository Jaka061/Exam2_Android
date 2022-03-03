package com.kay.progayim

import androidx.room.PrimaryKey

data class RepoResult(
    val results : List<Character>
)

data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val status: String?,
    val species: String,
    val type: String,
    val gender: String,
    val origin: ListCh,
    val location : ListCh,
    val image : String?,
    val episode : List<String>,
    val url : String,
    val created : String
)
data class ListCh(
    val name: String,
    val url: String
)
