package com.example.newsaggregator.domain.models

data class NewsModel(
    val title: String,
    val link: String,
    val description: String,
    val categories: List<String>,
    val pubDate: String,
)