package ru.rubt.data.converters

import ru.rubt.data.db.entities.HiNewsEntity
import ru.rubt.data.remote.model.HiNews
import javax.inject.Inject

class HiNewsConverter @Inject constructor() {

    fun convertToDb(theme: String, lstHiNews: List<HiNews>): List<HiNewsEntity> =
        lstHiNews.map {
            HiNewsEntity(
                    it.url, it.urlToImage,
                    it.title, it.author,
                    it.date, it.desc,
                    theme
            )
        }

}