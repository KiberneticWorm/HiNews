package ru.rubt.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.rubt.data.db.entities.HiNewsEntity

@Dao
interface HiNewsDao {

    @Insert
    fun insertHiNews(hiNewsEntity: HiNewsEntity)

    @Delete
    fun deleteHiNews(hiNewsEntity: HiNewsEntity)

    @Query("select * from hi_news_table")
    fun getHiNews(): List<HiNewsEntity>

}