package com.lucabelezal.newsapp.ui.article

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.lucabelezal.newsapp.R
import com.lucabelezal.newsapp.databinding.ActivityArticleBinding
import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.presenter.favorites.FavoritePresenter
import com.lucabelezal.newsapp.presenter.favorites.FavoriteView

class ArticleActivity: AppCompatActivity(), FavoriteView.View {

    private lateinit var binding: ActivityArticleBinding
    private lateinit var presenter: FavoritePresenter
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }

        binding.fab.setOnClickListener {
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