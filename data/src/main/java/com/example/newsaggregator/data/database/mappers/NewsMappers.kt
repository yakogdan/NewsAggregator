package com.example.newsaggregator.data.database.mappers

import com.example.newsaggregator.data.database.models.NewsDBO
import com.example.newsaggregator.domain.models.NewsModel

private fun NewsDBO.toModel(): NewsModel =
    NewsModel(
        title = title,
        newsUrl = newsUrl,
        description = description,
        pubDate = pubDate,
        imageUrl = imageUrl,
    )

fun List<NewsDBO>.toModels(): List<NewsModel> =
    map { it.toModel() }

private fun NewsModel.toDBO(): NewsDBO =
    NewsDBO(
        title = title,
        newsUrl = newsUrl,
        description = description,
        pubDate = pubDate,
        imageUrl = imageUrl,
    )

fun List<NewsModel>.toDBOs(): List<NewsDBO> =
    map { it.toDBO() }