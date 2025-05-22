package com.example.newsaggregator.domain.models

data class NewsModel(
    val title: String,
    val newsUrl: String,
    val description: String,
    val pubDate: String,
    val imageUrl: String?,
)