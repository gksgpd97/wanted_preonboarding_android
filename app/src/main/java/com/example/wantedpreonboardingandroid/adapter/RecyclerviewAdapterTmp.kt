package com.example.wantedpreonboardingandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.databinding.ItemRecyclerviewBinding
import com.example.wantedpreonboardingandroid.utilities.DateConvertor

class RecyclerviewAdapterTmp(
    val itemClick: (Article) -> Unit,
    val context: Context
) :
    PagingDataAdapter<Article, RecyclerviewAdapterTmp.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerviewAdapterTmp.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class ViewHolder(private val binding: ItemRecyclerviewBinding) :
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

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.url == newItem.url


            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }
}