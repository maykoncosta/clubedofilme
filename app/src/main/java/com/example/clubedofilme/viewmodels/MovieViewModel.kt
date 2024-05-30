package com.example.clubedofilme.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clubedofilme.models.Movie
import com.example.clubedofilme.repositories.MovieRepository

class MovieViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    private val _popularMovies = MutableLiveData<List<Movie>?>()
    val popularMovies: MutableLiveData<List<Movie>?> get() = _popularMovies

    private val _recommendedMovies = MutableLiveData<List<Movie>?>()
    val recommendedMovies: LiveData<List<Movie>?> get() = _recommendedMovies

    private val _latestMovies = MutableLiveData<List<Movie>?>()
    val latestMovies: MutableLiveData<List<Movie>?> get() = _latestMovies

    private val _searchResults = MutableLiveData<List<Movie>?>()
    val searchResults: MutableLiveData<List<Movie>?> get() = _searchResults

    fun fetchPopularMovies(page: Int) {
        movieRepository.fetchPopularMovies(page) { movies ->
            _popularMovies.postValue(movies)
        }
    }

    fun fetchLatestMovies(page: Int) {
        movieRepository.fetchLatestMovies(page) { movies ->
            _latestMovies.postValue(movies)
        }
    }

    fun fetchRecommendedMovies(page: Int) {
        // Supondo que o método fetchRecommendedMovies exista no MovieRepository
        movieRepository.fetchPopularMovies(page) { movies ->
            _recommendedMovies.postValue(movies)
        }
    }

    fun searchMovies(query: String, page: Int) {
        movieRepository.searchMovies(query, page) { movies ->
            _searchResults.postValue(movies)
        }
    }
}
