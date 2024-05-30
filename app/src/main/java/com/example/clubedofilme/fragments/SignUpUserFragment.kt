package com.example.clubedofilme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.clubedofilme.R
import com.example.clubedofilme.activities.SignUpActivity
import com.example.clubedofilme.databinding.FragmentSignUpUserBinding
import com.example.clubedofilme.utils.ToastUtils
import com.example.clubedofilme.viewmodels.SignUpViewModel

class SignUpUserFragment : Fragment() {

    private var _binding: FragmentSignUpUserBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val repeatPass = binding.repeatPasswordEditText.text.toString()

            if(validateUserData(username, email, password, repeatPass)) {
                changeUserData(username, email, password)
                (activity as SignUpActivity).navigateToFragment(SignUpMovieFragment())

            }else {
                showErrorMessage()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeUserData(username: String, email: String, password: String,) {
        signUpViewModel.setUsername(username)
        signUpViewModel.setEmail(email)
        signUpViewModel.setPassword(password)
    }

    private fun validateUserData(username: String,
                                 email: String,
                                 password: String,
                                 repeatPass: String): Boolean {
        if(username.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPass.isEmpty()) {
            return false
        }else {
            return password == repeatPass
        }
    }

    private fun showErrorMessage() {
        ToastUtils.showCustomToast(requireContext(), "Preencha todos os campos!", android.R.drawable.ic_dialog_alert)
    }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
