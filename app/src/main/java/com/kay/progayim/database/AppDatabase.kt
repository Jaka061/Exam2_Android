package com.kay.progayim.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Characters::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}