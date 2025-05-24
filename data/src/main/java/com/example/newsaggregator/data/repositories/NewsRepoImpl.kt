package com.example.newsaggregator.data.repositories

import com.example.newsaggregator.data.database.dao.NewsDao
import com.example.newsaggregator.data.database.mappers.toDBOs
import com.example.newsaggregator.data.database.mappers.toModels
import com.example.newsaggregator.data.network.api.RssFeed
import com.example.newsaggregator.data.network.mappers.toModels
import com.example.newsaggregator.domain.models.NewsModel
import com.example.newsaggregator.domain.repositories.NewsRepository
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsApi: RssFeed,
    private val newsDao: NewsDao,
) : NewsRepository {

    override suspend fun getNewsFromApi(): List<NewsModel> =
        newsApi.getRss().channel.items.toModels()

    override suspend fun getNewsFromDb(): List<NewsModel> =
        newsDao.getAll().toModels()

    override suspend fun refreshNewsInDb(news: List<NewsModel>) {
        newsDao.refresh(items = news.toDBOs())
    }
}