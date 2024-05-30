package com.example.clubedofilme.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.clubedofilme.R
import com.example.clubedofilme.activities.SignUpActivity
import com.example.clubedofilme.databinding.FragmentSignUpGenreBinding
import com.example.clubedofilme.models.Genre
import com.example.clubedofilme.repositories.MovieRepository
import com.example.clubedofilme.utils.ToastUtils
import com.example.clubedofilme.viewmodels.SignUpViewModel

class SignUpGenreFragment : Fragment() {

    private var _binding: FragmentSignUpGenreBinding? = null
    private val binding get() = _binding!!
    private val movieRepository = MovieRepository()
    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchGenres()

        binding.continueButton.setOnClickListener {
            val selectedGenres = listOf(
                binding.genreSpinner1.selectedItem as String,
                binding.genreSpinner2.selectedItem as String,
                binding.genreSpinner3.selectedItem as String,
                binding.genreSpinner4.selectedItem as String
            ).filter { it != "Selecione" }

            Log.v("SignUpGenreFragment", "Selected genres: $selectedGenres")

            if (selectedGenres.size > 4) {
                ToastUtils.showCustomToast(requireContext(), "Selecione até 4 generos!", android.R.drawable.ic_dialog_alert)
                return@setOnClickListener
            }

            signUpViewModel.setFavoriteGenres(selectedGenres)

            (activity as SignUpActivity).navigateToFragment(SignUpActorFragment())
        }
    }

    private fun fetchGenres() {
        movieRepository.fetchGenres() { genres ->
            if (genres != null) {
                val genreList = mutableListOf(Genre(0, "Selecione")).apply { addAll(genres) }
                setupSpinners(genreList)
            } else {
                ToastUtils.showCustomToast(requireContext(), "Erro ao carregar os generos!", android.R.drawable.ic_dialog_alert)
            }
        }
    }

    private fun setupSpinners(genres: List<Genre>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, genres.map { it.name })
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        binding.genreSpinner1.adapter = adapter
        binding.genreSpinner2.adapter = adapter
        binding.genreSpinner3.adapter = adapter
        binding.genreSpinner4.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
