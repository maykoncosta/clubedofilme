package com.example.clubedofilme.activities

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.clubedofilme.databinding.ActivityLoginBinding
import com.example.clubedofilme.utils.ToastUtils
import com.example.clubedofilme.viewmodels.AuthViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.authError.observe(this) { error ->
            error?.let {
                ToastUtils.showCustomToast(this, "Autenticacao com erro: $it", R.drawable.ic_dialog_alert)
            }
        }

        authViewModel.user.observe(this) { user ->
            user?.let {
                ToastUtils.showCustomToast(this, "Login feito com sucesso!", R.drawable.ic_dialog_info)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            authViewModel.loginUser(email, password)
        }

        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.forgotPasswordButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
