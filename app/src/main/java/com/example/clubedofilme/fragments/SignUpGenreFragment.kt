package com.example.clubedofilme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.clubedofilme.R
import com.example.clubedofilme.databinding.FragmentSignUpGenreBinding
import com.example.clubedofilme.viewmodels.SignUpViewModel

class SignUpGenreFragment : Fragment() {

    private var _binding: FragmentSignUpGenreBinding? = null
    private val binding get() = _binding!!

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

        // Exemplo de lista de gêneros
        val genres = arrayOf("Action", "Comedy", "Drama", "Horror", "Romance", "Sci-Fi", "Thriller")

        // Configurar os Spinners com a lista de gêneros
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.genreSpinner1.adapter = adapter
        binding.genreSpinner2.adapter = adapter
        binding.genreSpinner3.adapter = adapter
        binding.genreSpinner4.adapter = adapter

        binding.continueButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpActorFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
