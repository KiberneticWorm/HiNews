package ru.rubt.hinews

import android.app.Application
import ru.rubt.hinews.di.DaggerAppComponent

class App: Application() {

    val appComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(applicationContext)
    }

}