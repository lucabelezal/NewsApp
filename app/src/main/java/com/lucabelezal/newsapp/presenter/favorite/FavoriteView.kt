package com.lucabelezal.newsapp.presenter.favorite

import com.lucabelezal.newsapp.model.Article

interface FavoriteView {
    interface Presenter {
        fun showArticles(articles: List<Article>)
    }
}