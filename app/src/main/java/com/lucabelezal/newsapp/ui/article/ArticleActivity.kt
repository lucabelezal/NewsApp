package com.lucabelezal.newsapp.ui.article

import android.webkit.WebViewClient
import com.lucabelezal.newsapp.R
import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.ui.AbstractActivity
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity: AbstractActivity() {

    private lateinit var article: Article

    override fun getLayout(): Int = R.layout.activity_article

    override fun onInject() {
        getArticle()

        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }
    }

    private fun getArticle() {
        intent.extras?.let { articleSelected ->
            article = articleSelected.get("article") as Article
        }
    }
}