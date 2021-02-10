package com.lucabelezal.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucabelezal.newsapp.R
import com.lucabelezal.newsapp.model.Article
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnclickListener(listener: ((Article) -> Unit)?) {
        onItemClickListener = listener
    }

    private val  differCallback = object: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvSource.text = article.author ?: article.source?.name
            tvPublishedAt.text = article.publishedAt

            setOnclickListener {
                onItemClickListener?.let { clickAction ->
                    clickAction(article)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}