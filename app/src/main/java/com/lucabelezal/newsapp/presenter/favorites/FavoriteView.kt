package com.lucabelezal.newsapp.presenter.favorites

import com.lucabelezal.newsapp.model.Article

interface FavoriteView {
    interface View {
        fun showArticles(articles: List<Article>)
    }

    interface Presenter {
        fun onSuccess(articles: List<Article>)
    }
}