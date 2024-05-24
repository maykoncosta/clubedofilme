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

    private val selectedActors = mutableSetOf<Int>()

    interface OnItemClickListener {
        fun onItemClick(actorId: Int)
    }

    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val movieImageView: ImageView = itemView.findViewById(R.id.movieImageView)
        val checkImageView: ImageView = itemView.findViewById(R.id.checkImageView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val movie = actorList[position]
                if (selectedActors.contains(movie.id)) {
                    selectedActors.remove(movie.id)
                    itemView.alpha = 1.0f // Deselecionado
                    checkImageView.visibility = View.GONE
                } else {
                    selectedActors.add(movie.id)
                    checkImageView.visibility = View.VISIBLE
                    itemView.alpha = 0.8f // Selecionado
                }
                listener.onItemClick(movie.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ActorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actorList[position]
        if(actor.profilePath.isNullOrBlank()) return
        val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profilePath}"
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.image_not_found_icon) // Adicione uma imagem de placeholder
            .into(holder.movieImageView)
        holder.itemView.alpha = if (selectedActors.contains(actor.id)) 0.8f else 1.0f
        holder.checkImageView.visibility = if (selectedActors.contains(actor.id)) View.VISIBLE else View.GONE
    }

    override fun getItemCount() = actorList.size

    fun getSelectedActors(): List<Int> {
        return selectedActors.toList()
    }
}
