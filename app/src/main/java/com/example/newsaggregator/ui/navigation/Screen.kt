package com.example.newsaggregator.ui.navigation

import android.net.Uri

sealed class Screen(
    val route: String,
) {

    data object News : Screen(route = ROUTE_NEWS)

    data object NewsDetail : Screen(route = ROUTE_NEWS_DETAIL) {

        private const val ROUTE_FOR_ARGS = "news_detail"

        fun getRouteWithArgs(newsUrl: String): String {
            return "$ROUTE_FOR_ARGS/${newsUrl.encode()}"
        }
    }

    companion object {

        const val NEWS_URL_KEY = "news_url"

        const val ROUTE_NEWS = "news"
        const val ROUTE_NEWS_DETAIL = "news_detail/{$NEWS_URL_KEY}"
    }
}

fun String.encode(): String {
    return Uri.encode(this)
}