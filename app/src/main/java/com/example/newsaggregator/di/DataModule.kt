package com.example.newsaggregator.di

import com.example.newsaggregator.data.repositories.NewsRepoImpl
import com.example.newsaggregator.domain.repositories.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @[Binds Singleton]
    fun bindNewsRepository(impl: NewsRepoImpl): NewsRepository
}