package com.example.clubedofilme.repositories

import com.example.clubedofilme.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    suspend fun addUser(user: User) {
        usersCollection.document(user.userId).set(user).await()
    }

    suspend fun getUser(userId: String): User? {
        val document = usersCollection.document(userId).get().await()
        return document.toObject(User::class.java)
    }

    suspend fun updateUser(user: User) {
        usersCollection.document(user.userId).set(user).await()
    }

    suspend fun deleteUser(userId: String) {
        usersCollection.document(userId).delete().await()
    }
}
