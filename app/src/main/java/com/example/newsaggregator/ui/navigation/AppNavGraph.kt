package com.example.newsaggregator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsaggregator.ui.navigation.Screen.Companion.NEWS_URL_KEY

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsScreen: @Composable () -> Unit,
    newsDetailScreen: @Composable (String?) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.News.route,
    ) {

        composable(route = Screen.News.route) {
            newsScreen()
        }

        composable(
            route = Screen.NewsDetail.route,
            arguments = listOf(
                navArgument(NEWS_URL_KEY) {
                    type = NavType.StringType
                }
            ),
        ) {
            val newsUrl = it.arguments?.getString(NEWS_URL_KEY)
            newsDetailScreen(newsUrl)
        }
    }
}