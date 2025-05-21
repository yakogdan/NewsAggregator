package com.example.newsaggregator.ui.screens.news

import com.example.newsaggregator.domain.models.NewsModel

sealed interface NewsScreenState {

    data object Initial : NewsScreenState

    data object Loading : NewsScreenState

    data class Success(val news: List<NewsModel>) : NewsScreenState

    data class Error(val throwable: Throwable) : NewsScreenState
}