package ru.rubt.data.remote.model

import com.google.gson.annotations.SerializedName

data class HiNewsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: HiNewsData
)