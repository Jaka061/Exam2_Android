package com.kay.progayim

data class RepoResult(
    val results : List<Character>
)

data class Character(
    val id: Long?,
    val name: String,
    val status: String?,
    val species: String,
    val type: String,
    val gender: String,
    val origin: List<String>,
    val location : List<String>,
    val image : String,
    val episode : String,
    val created : String
)
