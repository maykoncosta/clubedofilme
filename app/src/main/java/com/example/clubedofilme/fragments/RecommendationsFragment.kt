package com.example.clubedofilme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.example.clubedofilme.R
import com.example.clubedofilme.repositories.GroupRepository
import com.example.clubedofilme.viewmodels.RecommendationsViewModel
import com.example.clubedofilme.viewmodels.RecommendationsViewModelFactory

class RecommendationsFragment : Fragment() {

    private lateinit var recommendationsViewModel: RecommendationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommendations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupRepository = GroupRepository()
        val factory = RecommendationsViewModelFactory(groupRepository)
        recommendationsViewModel = ViewModelProvider(this, factory).get(RecommendationsViewModel::class.java)

        recommendationsViewModel.groupData.observe(viewLifecycleOwner, Observer { group ->
            // Atualize a UI com os dados do grupo
        })
    }
}
