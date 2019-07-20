package com.malwinder.example.presentation.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.malwinder.example.R
import com.malwinder.example.domain.models.SearchResultItem
import com.malwinder.example.presentation.ui.base.BaseDiffAdapter
import com.malwinder.example.presentation.ui.base.VIEW_TYPE_NORMAL
import kotlinx.android.synthetic.main.item_search_result_item.view.*
import java.net.URLDecoder
import java.net.URLEncoder

class MainAdapter(var listener : ItemClickListener) : BaseDiffAdapter<SearchResultItem, RecyclerView.ViewHolder>() {
    interface ItemClickListener {
        fun onItemClicked(result: SearchResultItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_NORMAL)
            MainViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_search_result_item, parent, false))
         else LoadingViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loading, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_NORMAL) {
            val searchResultItem = getItem(position)
            val viewHolder = holder as MainViewHolder
            viewHolder.titleTextView.text = searchResultItem?.title?.replace("&#39;","'")
            viewHolder.descriptionTextView.text = viewHolder.descriptionTextView.context.getString(R.string.posted_by, searchResultItem?.owner?.displayName)
            viewHolder.tagsTextView.text = searchResultItem?.tags?.joinToString()
            viewHolder.itemView.setOnClickListener { listener.onItemClicked(searchResultItem!!) }
            Glide.with(viewHolder.profileImageView)
                    .load(searchResultItem?.owner?.profileImage)
                    .into(viewHolder.profileImageView)
        }
    }

    class MainViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val titleTextView = view.list_item_title_textView
        val descriptionTextView = view.list_item_description_textView
        val tagsTextView = view.list_item_tags
        val profileImageView = view.profile_imageView
    }

}