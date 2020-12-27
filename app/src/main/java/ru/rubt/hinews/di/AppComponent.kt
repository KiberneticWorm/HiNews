package ru.rubt.hinews.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.rubt.hinews.activities.MainActivity
import ru.rubt.hinews.di.modules.DatabaseModule
import ru.rubt.hinews.di.modules.RetrofitModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    RetrofitModule::class,
    DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ctx: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}