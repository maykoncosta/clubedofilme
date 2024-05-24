package com.example.clubedofilme.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.clubedofilme.R
import com.example.clubedofilme.databinding.ActivitySignUpBinding
import com.example.clubedofilme.fragments.SignUpUserFragment
import com.example.clubedofilme.viewmodels.AuthViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.authError.observe(this) { error ->
            error?.let {
                Toast.makeText(this, "Authentication failed: $it", Toast.LENGTH_SHORT).show()
            }
        }

        authViewModel.user.observe(this) { user ->
            Log.v("SignUpActivity", "User: $user")
            Toast.makeText(this, "Usuario Criado com Sucesso", Toast.LENGTH_SHORT).show()
            user?.let {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        // Inicia o primeiro fragmento de cadastro
        if (savedInstanceState == null) {
            navigateToFragment(SignUpUserFragment())
        }
    }

    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
