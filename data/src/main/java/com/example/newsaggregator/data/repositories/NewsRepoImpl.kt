package com.example.newsaggregator.data.repositories

import com.example.newsaggregator.data.network.api.RssFeed
import com.example.newsaggregator.data.network.mappers.toModels
import com.example.newsaggregator.domain.models.NewsModel
import com.example.newsaggregator.domain.repositories.NewsRepository
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsApi: RssFeed,
) : NewsRepository {

    override suspend fun getNewsFromApi(): List<NewsModel> =
        newsApi.getRss().channel.items.toModels()
}