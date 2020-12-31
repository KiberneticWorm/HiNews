package ru.rubt.newsfeature.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubt.data.repository.HiNewsRepository
import ru.rubt.newsfeature.viewmodels.HiNewsViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class HiNewsViewModelFactory @Inject constructor(
        private val hiNewsRepository: HiNewsRepository
): ViewModelProvider.Factory {

    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HiNewsViewModel::class.java)) {
            return HiNewsViewModel(hiNewsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}