package com.example.newsaggregator.ui.navigation

sealed class Screen(
    val route: String,
) {

    data object News : Screen(route = ROUTE_NEWS)
    data object NewsDetail : Screen(route = ROUTE_NEWS_DETAIL)

    companion object {
        const val ROUTE_NEWS = "news"
        const val ROUTE_NEWS_DETAIL = "news_detail"
    }
}