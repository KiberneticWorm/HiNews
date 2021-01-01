package ru.rubt.hinews

import android.app.Application
import ru.rubt.hinews.di.DaggerAppComponent
import ru.rubt.mainscreen.di.HiNewsActivityComponent
import ru.rubt.mainscreen.di.HiNewsActivityComponentProvider

import ru.rubt.newsfeature.di.HiNewsFragmentComponent
import ru.rubt.newsfeature.di.HiNewsFragmentComponentProvider
import ru.rubt.newsfeature.fragments.HiNewsFragment

class App: Application(), HiNewsFragmentComponentProvider, HiNewsActivityComponentProvider {

    val appComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(applicationContext)
    }

    override fun provideHiNewsActivityComponent(): HiNewsActivityComponent {
        return appComponent.hiNewsActivityComponent().create()
    }

    override fun provideHiNewsFragmentComponentProvider(): HiNewsFragmentComponent {
        return appComponent.hiNewsFragmentComponent().create()
    }

}