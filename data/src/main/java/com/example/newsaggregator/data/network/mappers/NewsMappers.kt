package com.example.newsaggregator.data.network.mappers

import com.example.newsaggregator.data.network.dto.ItemDto
import com.example.newsaggregator.data.network.utils.formatDate
import com.example.newsaggregator.data.network.utils.htmlToString
import com.example.newsaggregator.domain.models.NewsModel

private fun ItemDto.toModel(): NewsModel = NewsModel(
    title = title,
    newsUrl = link,
    description = description.htmlToString(),
    pubDate = dcDate.formatDate(),
    imageUrl = contents.lastOrNull()?.url,
)

fun List<ItemDto>.toModels(): List<NewsModel> = map { it.toModel() }