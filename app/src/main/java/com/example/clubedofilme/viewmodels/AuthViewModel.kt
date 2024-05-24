package com.example.clubedofilme.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> get() = _user

    private val _authError = MutableLiveData<String?>()
    val authError: LiveData<String?> get() = _authError

    fun registerUser(email: String, password: String, userInfo: HashMap<String, Any?>) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    firebaseUser?.let {
                        saveAdditionalUserInfo(it, userInfo)
                    }
                } else {
                    _authError.value = task.exception?.message
                }
            }
    }

    private fun saveAdditionalUserInfo(user: FirebaseUser, userInfo: HashMap<String, Any?>) {
        db.collection("users").document(user.uid).set(userInfo)
            .addOnSuccessListener {
                _user.value = user
            }
            .addOnFailureListener { e ->
                _authError.value = e.message
            }
    }

    fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                } else {
                    _authError.value = task.exception?.message
                }
            }
    }

    fun logoutUser() {
        auth.signOut()
        _user.value = null
    }
}
