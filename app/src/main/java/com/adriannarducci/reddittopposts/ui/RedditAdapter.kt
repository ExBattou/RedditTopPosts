package com.adriannarducci.reddittopposts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adriannarducci.reddittopposts.R
import com.adriannarducci.reddittopposts.models.RedditPost
import com.adriannarducci.reddittopposts.utils.DiffUtilCallBack
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adatpter_row.view.*

class RedditAdapter :
    PagingDataAdapter<RedditPost, RedditAdapter.RedditViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adatpter_row, parent, false)
        return RedditViewHolder(view)
    }

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class RedditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val scoreText: TextView = itemView.score
        private val commentsText: TextView = itemView.comments
        private val titleText: TextView = itemView.title
        private val imagePost: ImageView = itemView.image_post

        fun bindPost(redditPost: RedditPost) {
            with(redditPost) {
                scoreText.text = score.toString()
                commentsText.text = commentCount.toString()
                titleText.text = title
                Glide
                    .with(itemView)
                    .load(redditPost.urlPic)
                    .placeholder(R.color.white)
                    .into(imagePost)

            }
        }
    }
}