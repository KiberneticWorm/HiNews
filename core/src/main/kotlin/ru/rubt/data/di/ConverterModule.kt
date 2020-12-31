package ru.rubt.data.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class ConverterModule {

    @Provides
    fun providesGson()  = Gson()

}