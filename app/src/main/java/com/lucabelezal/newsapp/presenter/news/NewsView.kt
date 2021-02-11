package com.lucabelezal.newsapp.presenter.news

import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.model.NewsResponse

interface NewsView {
    interface View {
        fun hideProgressBar()
        fun showProgressBar()
        fun showArticles(articles: List<Article>)
        fun showFailure(message: String)
    }

    interface Presenter {
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}