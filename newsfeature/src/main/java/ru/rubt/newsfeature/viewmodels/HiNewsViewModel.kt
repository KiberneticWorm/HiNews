package ru.rubt.newsfeature.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.rubt.data.repository.HiNewsRepository
import ru.rubt.newsfeature.di.scopes.ShowHiNewsScope
import javax.inject.Inject

@ShowHiNewsScope
class HiNewsViewModel(
        private val hiNewsRepository: HiNewsRepository
): ViewModel() {

    fun getUpdatedHiNews(theme: String) = hiNewsRepository.getUpdatedHiNews(theme)
    fun getCachedHiNews() = hiNewsRepository.lstHiNewsEntity
}