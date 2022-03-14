package com.kay.progayim.database

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CharacterDao {

    @Query("SELECT * FROM Characters")
    fun getAll(): LiveData<List<Characters>>

    @Query("SELECT * FROM Characters WHERE id = :id")
    fun getById(id: Long): Single<Characters>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(characterL: List<Characters>)

    @Update
    fun update(characters: Characters)

    @Delete
    fun delete(characters: Characters)
}