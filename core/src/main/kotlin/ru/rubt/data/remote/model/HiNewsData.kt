package ru.rubt.data.remote.model

import com.google.gson.annotations.SerializedName

data class HiNewsData(
    @SerializedName("page_status") val pageStatus: String,
    @SerializedName("news") val news: List<HiNews>
)