package ru.rubt.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import ru.rubt.data.converters.HiNewsConverter
import ru.rubt.data.db.dao.HiNewsDao
import ru.rubt.data.db.entities.HiNewsEntity
import ru.rubt.data.local.AccessKeyManager
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
    private val hiNewsConverter: HiNewsConverter,
    private val accessKeyManager: AccessKeyManager
) {

    private val accessKey = accessKeyManager.getAccessKey()

    var currPageStatus = ""
    var lstHiNewsEntity: List<HiNewsEntity> = listOf()

    fun getUpdatedHiNews(theme: String): LiveData<UpdateState> {
        val data = MutableLiveData<UpdateState>()

        CoroutineScope(Dispatchers.IO).async {

            try {

                updateHiNews(theme)
                Log.d("TEST_", "sfsdfds")
                val hiNews = getHiNewsByTheme(theme)
                Log.d("TEST_", "received ${hiNews.size}")
                Log.d("TEST_", "dfgjdfiogjdf")
                data.postValue(UpdatedHiNewsState(hiNews))

                lstHiNewsEntity = hiNews

            } catch (exc: Exception) {

                Log.d("TEST_", exc.message.toString())

                val hiNews = getHiNewsByTheme(theme)

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
    private fun updateHiNews(theme: String) {

        val hiNewsResponse = hiNewsService.getHiNews(accessKey, theme)

        val responseBody = hiNewsResponse.execute().body() ?: failedUpdate()

        if (responseBody.status.equals(ResponseStatus.ERROR) ||
                responseBody.data.pageStatus.equals(PageStatus.NO_EXISTS)) {

            failedUpdate()

        }
        currPageStatus = responseBody.data.pageStatus
        val newsDb = hiNewsConverter.convertToDb(theme, responseBody.data.news)

        newsDb.forEach {
            hiNewsDao.insertHiNews(it)
        }

    }

    private fun failedUpdate(): Nothing {
        throw Exception()
    }

    private fun getHiNewsByTheme(theme: String) =  hiNewsDao.getHiNewsByTheme(theme)

}