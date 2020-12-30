package ru.rubt.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hi_news_table")
data class HiNewsEntity(
    var url: String,
    var urlToImage: String,
    var title: String,
    var author: String,
    var date: String,
    var desc: String,
    var content: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}