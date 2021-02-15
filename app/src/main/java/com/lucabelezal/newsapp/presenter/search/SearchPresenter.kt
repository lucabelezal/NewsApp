package com.lucabelezal.newsapp.presenter.search

import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.NewsResponse
import com.lucabelezal.newsapp.presenter.news.News

class SearchPresenter(
    val view: News.View,
    private val dataSource: NewsDataSource
): Search.Presenter {

    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.getSearchNews(term, this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}