package com.example.clubedofilme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clubedofilme.adapters.HomeMovieAdapter
import com.example.clubedofilme.databinding.FragmentViewListBinding
import com.example.clubedofilme.models.Movie
import com.example.clubedofilme.viewmodels.MovieViewModel

class ViewListFragment : Fragment(), HomeMovieAdapter.OnItemClickListener {

    private var _binding: FragmentViewListBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var homeMovieAdapter: HomeMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        homeMovieAdapter = HomeMovieAdapter(emptyList(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = homeMovieAdapter

        val sectionTitle = arguments?.getString(ARG_SECTION_TITLE) ?: "Movies"
        binding.tvSectionTitle.text = sectionTitle

        val movieType = arguments?.getString(ARG_MOVIE_TYPE) ?: TYPE_POPULAR
        observeMovies(movieType)

        fetchMovies(movieType, 1)
    }

    private fun observeMovies(movieType: String) {
        when (movieType) {
            TYPE_POPULAR -> {
                movieViewModel.popularMovies.observe(viewLifecycleOwner, Observer { movies ->
                    if (movies != null) {
                        homeMovieAdapter.updateMovies(movies)
                    }
                })
            }
            TYPE_RECOMMENDED -> {
                movieViewModel.recommendedMovies.observe(viewLifecycleOwner, Observer { movies ->
                    if(movies != null){
                        homeMovieAdapter.updateMovies(movies)
                    }
                })
            }
            TYPE_LATEST -> {
                movieViewModel.latestMovies.observe(viewLifecycleOwner, Observer { movies ->
                    if(movies != null){
                        homeMovieAdapter.updateMovies(movies)

                    }
                })
            }
        }
    }

    private fun fetchMovies(movieType: String, page: Int) {
        when (movieType) {
            TYPE_POPULAR -> movieViewModel.fetchPopularMovies(page)
            TYPE_RECOMMENDED -> movieViewModel.fetchRecommendedMovies(page)
            TYPE_LATEST -> movieViewModel.fetchLatestMovies(page)
        }
    }

    override fun onItemClick(movie: Movie) {
        // Implementar ação ao clicar no filme
    }

    companion object {
        const val ARG_SECTION_TITLE = "section_title"
        const val ARG_MOVIE_TYPE = "movie_type"
        const val TYPE_POPULAR = "popular"
        const val TYPE_RECOMMENDED = "recommended"
        const val TYPE_LATEST = "latest"

        fun newInstance(sectionTitle: String, movieType: String): ViewListFragment {
            val fragment = ViewListFragment()
            val args = Bundle()
            args.putString(ARG_SECTION_TITLE, sectionTitle)
            args.putString(ARG_MOVIE_TYPE, movieType)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
