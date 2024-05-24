package com.example.clubedofilme.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clubedofilme.R
import com.example.clubedofilme.models.Actor
import com.example.clubedofilme.models.Movie

class ActorAdapter(private val actorList: List<Actor>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movieId: Int)
    }

    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val movieImageView: ImageView = itemView.findViewById(R.id.movieImageView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(actorList[position].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ActorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actorList[position]
        val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profilePath}"
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.image_not_found_icon) // Adicione uma imagem de placeholder
            .into(holder.movieImageView)
    }

    override fun getItemCount() = actorList.size
}
