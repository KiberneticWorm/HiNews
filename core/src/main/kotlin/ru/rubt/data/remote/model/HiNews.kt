package ru.rubt.data.remote.model

data class HiNews(
    val url: String,
    val urlToImage: String,
    val title: String,
    val author: String,
    val date: String,
    val desc: String
)