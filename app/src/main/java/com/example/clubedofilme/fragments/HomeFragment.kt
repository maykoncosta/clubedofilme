package com.example.clubedofilme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.example.clubedofilme.R
import com.example.clubedofilme.databinding.FragmentHomeBinding
import com.example.clubedofilme.databinding.FragmentSignUpGenreBinding
import com.example.clubedofilme.models.Genre
import com.example.clubedofilme.repositories.UserRepository
import com.example.clubedofilme.viewmodels.HomeViewModel
import com.example.clubedofilme.viewmodels.HomeViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userRepository = UserRepository()
        val factory = HomeViewModelFactory(userRepository)
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        homeViewModel.userData.observe(viewLifecycleOwner, Observer { user ->
            // Atualize a UI com os dados do usuário
        })

        setupFragments()
        setupSpinners(listOf("Grupo 1", "Grupo 2", "Perfil 1"))
    }

    private fun setupFragments() {
        childFragmentManager.commit {
            add(R.id.fragmentContainer, ViewListFragment.newInstance("Popular", ViewListFragment.TYPE_POPULAR))
            add(R.id.fragmentContainer, ViewListFragment.newInstance("Recomendação", ViewListFragment.TYPE_RECOMMENDED))
            add(R.id.fragmentContainer, ViewListFragment.newInstance("Em Breve", ViewListFragment.TYPE_LATEST))
        }
    }

    private fun setupSpinners(profiles: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, profiles)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        binding.profileSpinner.adapter = adapter
    }


}
