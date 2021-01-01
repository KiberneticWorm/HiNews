package ru.rubt.newsfeature.viewmodels

import androidx.lifecycle.ViewModel
import ru.rubt.data.repository.HiNewsRepository
import ru.rubt.newsfeature.di.scopes.ShowHiNewsScope

@ShowHiNewsScope
class HiNewsViewModel(
        private val hiNewsRepository: HiNewsRepository
): ViewModel() {

    fun updateHiNews(theme: String) = hiNewsRepository.updateHiNews(theme)
    fun getCachedHiNews() = hiNewsRepository.lstCachedHiNews
}