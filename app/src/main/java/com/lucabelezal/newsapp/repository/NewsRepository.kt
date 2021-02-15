package com.lucabelezal.newsapp.repository

import com.lucabelezal.newsapp.db.ArticleDatabase
import com.lucabelezal.newsapp.model.Article

class NewsRepository(private val database: ArticleDatabase) {

    suspend fun updateInsert(article: Article) = database.getArticleDAO().updateInsert(article)

    suspend fun delete(article: Article) = database.getArticleDAO().delete(article)

    fun getAll(): List<Article> = database.getArticleDAO().getAll()
}