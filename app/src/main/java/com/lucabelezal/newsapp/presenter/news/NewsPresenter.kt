package com.lucabelezal.newsapp.presenter.news

import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.NewsResponse

class NewsPresenter(
    val view: News.View,
    private val dataSource: NewsDataSource
    ): News.Presenter {

    override fun requestAll() {
       this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)
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
