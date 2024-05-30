package com.example.clubedofilme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clubedofilme.R
import com.example.clubedofilme.databinding.FragmentUserBinding
import com.example.clubedofilme.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editPreferencesButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EditPreferencesFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.logoutButton.setOnClickListener {
            // Implementar logout
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
