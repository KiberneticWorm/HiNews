package ru.rubt.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.rubt.data.remote.model.HiNews

interface HiNewsService {

    @GET("/news.php")
    fun getHiNews(
        @Query("access_key") accessKey: String,
        @Query("theme") theme: String
    ): Call<List<HiNews>>

}