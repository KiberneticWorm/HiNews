package ru.rubt.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.rubt.data.remote.model.HiNews
import ru.rubt.data.remote.model.HiNewsResponse

interface HiNewsService {

    @GET("get_news.php")
    fun getHiNews(
        @Query("access_key") accessKey: String,
        @Query("theme") theme: String,
        @Query("page") page: Int = 1
    ): Call<HiNewsResponse>

    @GET("search.php")
    fun searchHiNews(
        @Query("access_key") accessKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): Call<HiNewsResponse>

}