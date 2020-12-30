package ru.rubt.hinews

import android.app.Application
import ru.rubt.hinews.di.DaggerAppComponent

import ru.rubt.newsfeature.di.HiNewsComponent
import ru.rubt.newsfeature.di.HiNewsComponentProvider

class App: Application(), HiNewsComponentProvider {

    val appComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(applicationContext)
    }

    override fun provideHiNewsComponent(): HiNewsComponent {
        return appComponent.hiNewsComponent().create()
    }
}