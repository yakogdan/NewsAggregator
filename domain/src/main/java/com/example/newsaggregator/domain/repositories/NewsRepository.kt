package com.example.newsaggregator.domain.repositories

import com.example.newsaggregator.domain.models.NewsModel

interface NewsRepository {

    suspend fun getNewsFromApi(): List<NewsModel>

    suspend fun getNewsFromDb(): List<NewsModel>

    suspend fun refreshNewsInDb(news: List<NewsModel>)
}