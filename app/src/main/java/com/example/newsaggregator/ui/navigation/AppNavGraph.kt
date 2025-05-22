package com.example.newsaggregator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsScreen: @Composable () -> Unit,
    newsDetailScreen: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.News.route,
    ) {

        composable(route = Screen.News.route) {
            newsScreen()
        }

        composable(route = Screen.NewsDetail.route) {
            newsDetailScreen()
        }
    }
}