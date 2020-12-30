package ru.rubt.newsfeature.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.rubt.data.repository.HiNewsRepository
import ru.rubt.newsfeature.di.scopes.ShowHiNewsScope
import javax.inject.Inject

@ShowHiNewsScope
class HiNewsViewModel: ViewModel() {

    @Inject
    lateinit var hiNewsRepository: HiNewsRepository

    fun getUpdatedHiNews() = hiNewsRepository.getUpdatedHiNews()
    fun getCachedHiNews() = hiNewsRepository.lstHiNewsEntity
}