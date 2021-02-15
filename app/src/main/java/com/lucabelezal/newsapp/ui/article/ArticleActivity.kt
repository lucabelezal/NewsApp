package com.lucabelezal.newsapp.ui.article

import android.webkit.WebViewClient
import com.google.android.material.snackbar.Snackbar
import com.lucabelezal.newsapp.R
import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.presenter.favorites.FavoritePresenter
import com.lucabelezal.newsapp.presenter.favorites.FavoriteView
import com.lucabelezal.newsapp.ui.base.AbstractActivity
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity: AbstractActivity(), FavoriteView.View {

    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter

    override fun getLayout(): Int = R.layout.activity_article

    override fun onInject() {
        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)

        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }

        fab.setOnClickListener {
            presenter.saveArticle(article)
            Snackbar.make(
                it,
                R.string.article_saved_successful,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun getArticle() {
        intent.extras?.let { articleSelected ->
            article = articleSelected.get("article") as Article
        }
    }

    override fun showArticles(articles: List<Article>) {}
}