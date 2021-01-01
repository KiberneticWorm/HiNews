package ru.rubt.mainscreen.di

import dagger.Module
import ru.rubt.newsfeature.di.HiNewsFragmentComponent

@Module(subcomponents = [HiNewsActivityComponent::class, HiNewsFragmentComponent::class])
class Subcomponents {}