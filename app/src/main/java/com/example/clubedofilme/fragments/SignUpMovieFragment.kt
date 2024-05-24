package com.example.clubedofilme.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clubedofilme.R
import com.example.clubedofilme.adapters.MovieAdapter
import com.example.clubedofilme.databinding.FragmentSignUpMovieBinding
import com.example.clubedofilme.models.Movie
import com.example.clubedofilme.repositories.MovieRepository
import com.example.clubedofilme.utils.EndlessRecyclerViewScrollListener
import com.example.clubedofilme.viewmodels.SignUpViewModel

class SignUpMovieFragment : Fragment(), MovieAdapter.OnItemClickListener {

    private var _binding: FragmentSignUpMovieBinding? = null
    private val binding get() = _binding!!
    private val movieRepository = MovieRepository()
    private lateinit var movieAdapter: MovieAdapter
    private val movieList = mutableListOf<Movie>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private var currentQuery: String? = null
    private var currentPage = 1

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(movieList, this)
        val layoutManager = GridLayoutManager(requireContext(), 3)

        binding.moviesRecyclerView.layoutManager = layoutManager
        binding.moviesRecyclerView.adapter = movieAdapter

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (currentQuery.isNullOrEmpty()) {
                    fetchMovies(page)
                } else {
                    searchMovies(currentQuery!!, page)
                }
            }
        }

        binding.moviesRecyclerView.addOnScrollListener(scrollListener)

        fetchMovies(1)

        binding.continueButton.setOnClickListener {
            val selectedMovies = movieAdapter.getSelectedMovies()
            signUpViewModel.setFavoriteMovies(selectedMovies.toList())
            Log.v("SignUpMovieFragment", "selectedMovies ${signUpViewModel.favoriteMovies.value}")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpGenreFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentQuery = s.toString()
                currentPage = 1
                movieList.clear()
                movieAdapter.notifyDataSetChanged()
                if (currentQuery.isNullOrEmpty()) {
                    fetchMovies(1)
                } else {
                    searchMovies(currentQuery!!, 1)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchMovies(page: Int) {
        movieRepository.fetchPopularMovies(page) { movies ->
            Log.v("SignUpMovieFragment", "fetchMovies ${signUpViewModel.username.value}")
            if (movies != null) {
                movieList.addAll(movies)
                movieAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Failed to load movies", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchMovies(query: String, page: Int) {
        movieRepository.searchMovies(query, page) { movies ->
            Log.v("SignUpMovieFragment", "searchMovies")
            if(page == 1) {
                movieList.clear()
            }
            if (movies != null) {
                movieList.addAll(movies)
                movieAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Failed to search movies", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClick(movieId: Int) {
        // Implement action on movie item click
    }
}
