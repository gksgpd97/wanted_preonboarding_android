package com.example.wantedpreonboardingandroid.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.databinding.ItemRecyclerviewBinding
import com.example.wantedpreonboardingandroid.utilities.DateConvertor

class RecyclerviewAdapter(
    val itemClick: (Article) -> Unit,
    val context: Context
) :
    RecyclerView.Adapter<RecyclerviewAdapter.Holder>() {

    private var articles: ArrayList<Article> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setArticles(articles: ArrayList<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            if (item.urlToImage != null) {
                Glide.with(context).load(item.urlToImage)
                    .into(binding.imageviewTopnewsitemThumbnail)
            } else {
                binding.imageviewTopnewsitemThumbnail.visibility = View.GONE
            }
            binding.textviewTopnewsitemTitle.text = item.title
            binding.textviewTopnewsitemAuthor.text = item.author
            binding.textviewTopnewsitemPublishedAt.text =
                DateConvertor().dateConvertor(item.publishedAt.toString())

            binding.root.setOnClickListener {
                itemClick(item)
            }
        }
    }
}