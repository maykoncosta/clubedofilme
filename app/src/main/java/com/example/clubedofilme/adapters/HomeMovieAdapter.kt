package com.example.clubedofilme.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clubedofilme.R
import com.example.clubedofilme.models.Movie
import java.text.DecimalFormat

class HomeMovieAdapter(private var movies: List<Movie>, private val listener: OnItemClickListener) : RecyclerView.Adapter<HomeMovieAdapter.MovieViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val movieImageView: ImageView = itemView.findViewById(R.id.movieImageView)
        val movieRatingTextView: TextView = itemView.findViewById(R.id.movieRatingTextView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val movie = movies[position]
                listener.onItemClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view_home, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        if (movie.posterPath == null) return
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.image_not_found_icon) // Adicione uma imagem de placeholder
            .into(holder.movieImageView)
        holder.movieRatingTextView.text = formatRating(movie.rating)
    }

    override fun getItemCount() = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    private fun formatRating(rating: Double): String {
        val decimalFormat = DecimalFormat("#.#")
        return decimalFormat.format(rating)
    }
}
