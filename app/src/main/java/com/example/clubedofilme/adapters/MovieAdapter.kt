package com.example.clubedofilme.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clubedofilme.R
import com.example.clubedofilme.models.Movie

class MovieAdapter(private val movieList: List<Movie>, private val listener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val selectedMovies = mutableSetOf<Int>()

    interface OnItemClickListener {
        fun onItemClick(movieId: Int)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val movieImageView: ImageView = itemView.findViewById(R.id.movieImageView)
        val checkImageView: ImageView = itemView.findViewById(R.id.checkImageView)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val movie = movieList[position]
                if (selectedMovies.contains(movie.id)) {
                    selectedMovies.remove(movie.id)
                    itemView.alpha = 1.0f // Deselecionado
                    checkImageView.visibility = View.GONE
                } else {
                    selectedMovies.add(movie.id)
                    checkImageView.visibility = View.VISIBLE
                    itemView.alpha = 0.8f // Selecionado
                }
                listener.onItemClick(movie.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        if(movie.posterPath == null) return
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.image_not_found_icon) // Adicione uma imagem de placeholder
            .into(holder.movieImageView)
        holder.itemView.alpha = if (selectedMovies.contains(movie.id)) 0.8f else 1.0f
        holder.checkImageView.visibility = if (selectedMovies.contains(movie.id)) View.VISIBLE else View.GONE
    }

    override fun getItemCount() = movieList.size

    fun getSelectedMovies(): List<Int> {
        return selectedMovies.toList()
    }
}
