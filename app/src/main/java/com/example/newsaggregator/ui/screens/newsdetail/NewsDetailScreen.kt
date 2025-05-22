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
fun NewsDetailScreen(newsUrl: String?) {
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
            val url = newsUrl ?: GUARDIAN_DEFAULT_ULR
            webView.loadUrl(url)
        }
    )
}

private const val GUARDIAN_DEFAULT_ULR = "https://www.theguardian.com/international"