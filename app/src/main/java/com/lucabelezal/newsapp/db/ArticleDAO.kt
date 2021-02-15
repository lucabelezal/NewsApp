package com.lucabelezal.newsapp.db

import androidx.room.*
import com.lucabelezal.newsapp.model.Article

@Dao
interface ArticleDAO {
    @Delete
    suspend fun delete(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAll(): List<Article>
}