package com.kay.progayim.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kay.progayim.ListCh

@Entity
data class Characters(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    val status: String?,
    val species: String,
    val type: String,
    val gender: String,
    val location :String,
    val image : String?,
    val url : String,
    val created : String
)