package com.example.newsaggregator.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.newsaggregator.ui.navigation.AppNavGraph
import com.example.newsaggregator.ui.screens.news.NewsScreen
import com.example.newsaggregator.ui.screens.newsdetail.NewsDetailScreen

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()

    AppNavGraph(
        navHostController = navHostController,

        newsScreen = {
            NewsScreen(
                navHostController = navHostController,
            )
        },

        newsDetailScreen = {
            NewsDetailScreen()
        },
    )
}