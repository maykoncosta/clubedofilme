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
import com.example.clubedofilme.adapters.ActorAdapter
import com.example.clubedofilme.databinding.FragmentSignUpActorBinding
import com.example.clubedofilme.models.Actor
import com.example.clubedofilme.repositories.MovieRepository
import com.example.clubedofilme.utils.EndlessRecyclerViewScrollListener
import com.example.clubedofilme.viewmodels.SignUpViewModel

class SignUpActorFragment : Fragment(), ActorAdapter.OnItemClickListener {

    private var _binding: FragmentSignUpActorBinding? = null
    private val binding get() = _binding!!
    private val movieRepository = MovieRepository()
    private lateinit var actorAdapter: ActorAdapter
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val actorList = mutableListOf<Actor>()
    private var currentQuery: String? = null
    private var currentPage = 1

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpActorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actorAdapter = ActorAdapter(actorList, this)
        val layoutManager = GridLayoutManager(requireContext(), 3)

        binding.actorsRecyclerView.layoutManager = layoutManager
        binding.actorsRecyclerView.adapter = actorAdapter

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (currentQuery.isNullOrEmpty()) {
                    fetchActors(page)
                } else {
                    searchActors(currentQuery!!, page)
                }
            }
        }

        binding.actorsRecyclerView.addOnScrollListener(scrollListener)

        fetchActors(1)

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentQuery = s.toString()
                currentPage = 1
                actorList.clear()
                actorAdapter.notifyDataSetChanged()
                if (currentQuery.isNullOrEmpty()) {
                    fetchActors(1)
                } else {
                    searchActors(currentQuery!!, 1)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchActors(page: Int) {
        movieRepository.fetchPopularActors(page) { actors ->
            Log.v("SignUpActorFragment", "fetchActors")
            if (actors != null) {
                actorList.addAll(actors)
                actorAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Faila ao carregar os atores", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchActors(query: String, page: Int) {
        movieRepository.searchActors(query, page) { actors ->
            Log.v("SignUpActorFragment", "searchActors")
            if (actors != null) {
                actorList.addAll(actors)
                actorAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Faila ao carregar os atores", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClick(actorId: Int) {
        // Implement action on actor item click
    }
}
