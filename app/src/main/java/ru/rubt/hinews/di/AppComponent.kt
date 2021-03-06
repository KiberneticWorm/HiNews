package ru.rubt.hinews.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.rubt.data.di.ConverterModule
import ru.rubt.data.di.RemoteModule
import ru.rubt.hinews.di.modules.DatabaseModule
import ru.rubt.hinews.di.modules.RetrofitModule
import ru.rubt.mainscreen.di.HiNewsActivityComponent
import ru.rubt.mainscreen.di.Subcomponents
import ru.rubt.newsfeature.di.HiNewsFragmentComponent
import javax.inject.Singleton


@Singleton
@Component(modules = [
    RetrofitModule::class, RemoteModule::class,
    ConverterModule::class, DatabaseModule::class,
    Subcomponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ctx: Context): AppComponent
    }

    fun hiNewsActivityComponent(): HiNewsActivityComponent.Factory
    fun hiNewsFragmentComponent(): HiNewsFragmentComponent.Factory

}