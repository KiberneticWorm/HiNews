package ru.rubt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.rubt.data.db.dao.HiNewsDao
import ru.rubt.data.db.entities.HiNewsEntity

@Database(entities = [HiNewsEntity::class], version = 1)
abstract class HiNewsDatabase: RoomDatabase() {
    abstract fun hiNewsDao(): HiNewsDao
}