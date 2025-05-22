package com.example.newsaggregator.data.network.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.formatDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val outputFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())
    outputFormat.timeZone = TimeZone.getDefault()

    return try {
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: this
    } catch (_: Exception) {
        this
    }
}