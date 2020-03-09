package com.codex.topstory.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.codex.topstory.R
import com.codex.topstory.models.Item

class FavoriteAdapter(
    private var listFavorite: List<Item>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    interface OnItemClickListener {
        fun onClick(story: Item)
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvFav: CardView = itemView.findViewById(R.id.cvFav)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = listFavorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val data = listFavorite[position]
        holder.tvTitle.text = data.title
        holder.cvFav.setOnClickListener {
            listener.onClick(data)
        }
    }

    fun updateData(newList: List<Item>?) {
        newList?.let {
            listFavorite = it
            notifyDataSetChanged()
        }
    }
}