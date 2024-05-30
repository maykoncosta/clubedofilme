package com.example.clubedofilme.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.clubedofilme.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openFragmentUserInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openFragmentUserInfo() {
        val fragmentUserInfo: UserInfoFragment = UserInfoFragment()
        val transaction: FragmentTransaction
        = parentFragmentManager.beginTransaction()
        transaction.replace(com.example.clubedofilme.R.id.fragment_container, fragmentUserInfo)
        transaction.addToBackStack(null) // Adicione a transação à pilha de volta para permitir a navegação reversa
        transaction.commit()
    }

}
