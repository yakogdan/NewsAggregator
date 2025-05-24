package com.example.newsaggregator.di

import android.content.Context
import com.example.newsaggregator.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context = context)
    }

    @[Provides Singleton]
    fun provideNewsDao(appDatabase: AppDatabase) = appDatabase.newsDao()
}