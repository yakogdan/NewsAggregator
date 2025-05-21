package com.example.newsaggregator.data.network.mappers

import com.example.newsaggregator.data.network.dto.ItemDto
import com.example.newsaggregator.data.network.utils.htmlToString
import com.example.newsaggregator.domain.models.NewsModel

private fun ItemDto.toModel(): NewsModel = NewsModel(
    title = title,
    link = link,
    description = description.htmlToString(),
    pubDate = dcDate,
)

fun List<ItemDto>.toModels(): List<NewsModel> = map { it.toModel() }