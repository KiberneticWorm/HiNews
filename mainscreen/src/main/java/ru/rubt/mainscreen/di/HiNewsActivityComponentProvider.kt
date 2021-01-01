package ru.rubt.mainscreen.di

import ru.rubt.newsfeature.di.HiNewsFragmentComponent

interface HiNewsActivityComponentProvider {
    fun provideHiNewsActivityComponent(): HiNewsActivityComponent
}