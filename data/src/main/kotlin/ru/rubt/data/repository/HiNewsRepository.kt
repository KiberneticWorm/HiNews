package ru.rubt.data.repository

import ru.rubt.data.db.dao.HiNewsDao
import ru.rubt.data.remote.HiNewsService
import javax.inject.Inject

class HiNewsRepository @Inject constructor(
    hiNewsDao: HiNewsDao,
    hiNewsService: HiNewsService
) {

}