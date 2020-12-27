package ru.rubt.hinews.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.rubt.data.db.HiNewsDatabase
import ru.rubt.data.db.dao.HiNewsDao

@Module
class DatabaseModule {

    @Provides
    fun providesHiNewsDatabase(ctx: Context): HiNewsDatabase =
        Room.databaseBuilder(
            ctx, HiNewsDatabase::class.java, "appDatabase")
            .build()

    @Provides
    fun providesHiNewsDao(hiNewsDatabase: HiNewsDatabase): HiNewsDao =
        hiNewsDatabase.hiNewsDao()


}