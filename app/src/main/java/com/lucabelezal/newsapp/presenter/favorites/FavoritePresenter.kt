package com.lucabelezal.newsapp.presenter.favorites

import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.Article

class FavoritePresenter(
    val view: FavoriteView.View,
    private val dataSource: NewsDataSource
) : FavoriteView.Presenter {


    fun getAll() {
        this.dataSource.fetchAllArticle(this)
    }

    fun saveArticle(article: Article) {
        this.dataSource.saveArticle(article)
    }

    fun deleteArticle(article: Article) {
        this.dataSource.deleteArticle(article)
    }

    override fun onSuccess(articles: List<Article>) {
        this.view.showArticles(articles)
    }
}