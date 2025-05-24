package com.example.newsaggregator.data.network.utils

import org.jsoup.Jsoup

fun String.htmlToString(): String =
    Jsoup.parse(this).selectFirst("p")
        ?.text()
        ?: "There is no description"