package com.kl3jvi.animity.di

import android.content.Context
import com.kl3jvi.animity.persistence.AnimeDao
import com.kl3jvi.animity.persistence.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PersistenceModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideAnimesDao(appDatabase: AppDatabase): AnimeDao {
        return appDatabase.animeDao()
    }
}
