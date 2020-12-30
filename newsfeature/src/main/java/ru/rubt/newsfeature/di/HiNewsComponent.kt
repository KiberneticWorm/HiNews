package ru.rubt.newsfeature.di

import dagger.Subcomponent
import ru.rubt.newsfeature.di.scopes.ShowHiNewsScope
import ru.rubt.newsfeature.fragments.HiNewsFragment

@ShowHiNewsScope
@Subcomponent
interface HiNewsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HiNewsComponent
    }

    fun inject(fragment: HiNewsFragment)

}