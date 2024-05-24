package com.example.clubedofilme.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clubedofilme.R
import com.example.clubedofilme.databinding.ActivitySignUpBinding
import com.example.clubedofilme.fragments.SignUpUserFragment

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicia o primeiro fragmento de cadastro
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpUserFragment())
                .commit()
        }
    }
}
