package com.example.newsaggregator.di

import com.example.newsaggregator.data.network.api.RssFeed
import com.example.newsaggregator.data.network.api.RssFeedFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton]
    fun provideRssFeed(): RssFeed = RssFeedFactory.guardian
}