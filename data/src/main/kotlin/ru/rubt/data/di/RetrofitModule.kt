package ru.rubt.hinews.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://rubteh.ru/rub2time")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}