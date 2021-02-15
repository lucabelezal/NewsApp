package com.lucabelezal.newsapp.ui.favorites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lucabelezal.newsapp.R
import com.lucabelezal.newsapp.adapter.NewsAdapter
import com.lucabelezal.newsapp.databinding.ActivityFavoritesBinding
import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.presenter.favorites.FavoritePresenter
import com.lucabelezal.newsapp.presenter.favorites.FavoriteView
import com.lucabelezal.newsapp.ui.article.ArticleActivity


class FavoritesActivity: AppCompatActivity(), FavoriteView.View {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var presenter: FavoritePresenter

    private val favoritesAdapter by lazy {
        NewsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)
        presenter.getAll()
        configRecycle()
        clickAdapter()


        val itemTouchPerCallback = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = favoritesAdapter.differ.currentList[position]
                presenter.deleteArticle(article)
                Snackbar.make(
                    viewHolder.itemView, R.string.article_delete_successful,
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction(getString(R.string.undo)) {
                        presenter.saveArticle(article)
                        favoritesAdapter.notifyDataSetChanged()
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchPerCallback).apply {
            attachToRecyclerView(binding.rvFavorites)
        }

        presenter.getAll()
    }

    override fun showArticles(articles: List<Article>) {
        favoritesAdapter.differ.submitList(articles.toList())
    }

    private fun configRecycle() {
        with(binding.rvFavorites) {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@FavoritesActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun clickAdapter() {
        favoritesAdapter.setOnclickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }
}