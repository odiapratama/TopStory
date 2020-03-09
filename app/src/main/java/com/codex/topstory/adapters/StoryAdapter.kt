package com.codex.topstory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.codex.topstory.R
import com.codex.topstory.models.Item

class StoryAdapter(
    private var listItem: List<Item>,
    private val context: Context,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    interface OnItemClickListener {
        fun onClick(item: Item)
    }

    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvStory: CardView = itemView.findViewById(R.id.cvStory)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvComment: TextView = itemView.findViewById(R.id.tvComment)
        val tvScore: TextView = itemView.findViewById(R.id.tvScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return StoryViewHolder(view)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = listItem[position]
        holder.tvTitle.text = story.title
        holder.tvComment.text = String.format(
            context.getString(R.string.total_comment),
            story.kids?.size?.toString() ?: "0"
        )
        holder.tvScore.text = String.format(
            context.getString(R.string.total_score),
            story.score?.toString() ?: "0"
        )
        holder.cvStory.setOnClickListener {
            listener.onClick(story)
        }
    }

    fun updateData(newList: List<Item>?) {
        newList?.let {
            listItem = it
            notifyDataSetChanged()
        }
    }
}