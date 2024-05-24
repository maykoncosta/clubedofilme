package com.example.clubedofilme.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clubedofilme.models.Actor
import com.example.clubedofilme.models.Movie

class SignUpViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _favoriteMovies = MutableLiveData<List<Int>>()
    val favoriteMovies: LiveData<List<Int>> get() = _favoriteMovies

    private val _favoriteActors = MutableLiveData<List<Int>>()
    val favoriteActors: LiveData<List<Int>> get() = _favoriteActors

    private val _favoriteGenres = MutableLiveData<List<String>>()
    val favoriteGenres: LiveData<List<String>> get() = _favoriteGenres

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setFavoriteMovies(movies: List<Int>) {
        _favoriteMovies.value = movies
    }

    fun setFavoriteActors(actors: List<Int>) {
        _favoriteActors.value = actors
    }

    fun setFavoriteGenres(genres: List<String>) {
        _favoriteGenres.value = genres
    }
}
