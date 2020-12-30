package ru.rubt.data.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.rubt.data.remote.HiNewsService
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun providesHiNewsService(retrofit: Retrofit): HiNewsService =
            retrofit.create(HiNewsService::class.java)

}