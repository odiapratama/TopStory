package com.codex.topstory.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codex.topstory.R
import com.codex.topstory.models.Item
import com.codex.topstory.utils.setTextHtml

class CommentAdapter(private var listComment: List<Item>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvComment: TextView = itemView.findViewById(R.id.tvComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listComment.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val data = listComment[position]
        holder.tvComment.setTextHtml(data.text)
    }

    fun updateData(newList: List<Item>?) {
        newList?.let {
            listComment = it
            notifyDataSetChanged()
        }
    }
}