package ru.rubt.mainscreen.di

import dagger.Subcomponent
import ru.rubt.mainscreen.activities.MainActivity
import ru.rubt.newsfeature.di.scopes.ShowHiNewsScope
import ru.rubt.newsfeature.fragments.HiNewsFragment

@ShowHiNewsScope
@Subcomponent
interface HiNewsActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HiNewsActivityComponent
    }

    fun inject(activity: MainActivity)

}