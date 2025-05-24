package com.example.newsaggregator.domain.usecases

import com.example.newsaggregator.domain.models.NewsModel
import com.example.newsaggregator.domain.repositories.NewsRepository
import javax.inject.Inject

class RefreshNewsInDbUseCase @Inject constructor(
    private var repository: NewsRepository,
) {
    suspend fun invoke(news: List<NewsModel>) {
        repository.refreshNewsInDb(news = news)
    }
}