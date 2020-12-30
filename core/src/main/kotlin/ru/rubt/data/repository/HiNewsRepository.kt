package ru.rubt.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import ru.rubt.data.converters.HiNewsConverter
import ru.rubt.data.db.dao.HiNewsDao
import ru.rubt.data.db.entities.HiNewsEntity
import ru.rubt.data.remote.HiNewsService
import ru.rubt.data.remote.states.*
import ru.rubt.data.remote.status.PageStatus
import ru.rubt.data.remote.status.ResponseStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HiNewsRepository @Inject constructor(
    private val hiNewsDao: HiNewsDao,
    private val hiNewsService: HiNewsService,
    private val hiNewsConverter: HiNewsConverter
) {

    var currPageStatus = ""
    var lstHiNewsEntity: List<HiNewsEntity> = listOf()

    fun getUpdatedHiNews(): LiveData<UpdateState> {
        val data = MutableLiveData<UpdateState>()

        CoroutineScope(Dispatchers.IO).async {

            try {

                updateHiNews()

                val hiNews = getHiNews()
                data.postValue(UpdatedHiNewsState(hiNews))

                lstHiNewsEntity = hiNews

            } catch (exc: Exception) {

                val hiNews = getHiNews()

                if (hiNews.isEmpty()) {
                    data.postValue(EmptyHiNewsState)
                } else {
                    data.postValue(NoUpdatedHiNewsState(hiNews))
                }

            }
        }
        return data
    }

    @Throws
    private fun updateHiNews() {

        val hiNewsResponse = hiNewsService.getHiNews(
                "5EF29C5B80E9DD550FEC2206192CFAA42535CD1D242AE146",
                "mars")

        Log.d("TEST_", hiNewsResponse.request().url().toString())

        val responseBody = hiNewsResponse.execute().body() ?: failedUpdate()

        Log.d("TEST_", responseBody.data.toString())

        if (responseBody.status.equals(ResponseStatus.ERROR) ||
                responseBody.data.pageStatus.equals(PageStatus.NO_EXISTS)) {

            failedUpdate()

        }

        currPageStatus = responseBody.data.pageStatus
        val newsDb = hiNewsConverter.convertToDb(responseBody.data.news)
        newsDb.forEach {
            hiNewsDao.insertHiNews(it)
        }

    }

    private fun failedUpdate(): Nothing {
        throw Exception()
    }

    private fun getHiNews() =  hiNewsDao.getHiNews()

}