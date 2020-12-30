package ru.rubt.hinews.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rubt.data.remote.HiNewsService
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl("https://rubteh.ru/rub2time/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
}