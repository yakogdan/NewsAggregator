package com.example.newsaggregator.ui.screens.newsdetail

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsDetailScreen(url: String = "https://www.theguardian.com/lifeandstyle/2025/may/20/i-am-taking-beta-blockers-for-my-anxiety-and-so-are-many-of-my-friends-is-that-a-problem") { // TODO: убрать ссылку
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    setSupportZoom(true)
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}