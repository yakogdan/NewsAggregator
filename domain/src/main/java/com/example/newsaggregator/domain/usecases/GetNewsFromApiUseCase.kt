package com.example.newsaggregator.domain.usecases

import com.example.newsaggregator.domain.models.NewsModel
import com.example.newsaggregator.domain.repositories.NewsRepository
import javax.inject.Inject

class GetNewsFromApiUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend fun invoke(): List<NewsModel> {
        return repository.getNewsFromApi()
    }
}