package com.example.clubedofilme.repositories

import com.example.clubedofilme.models.Group
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class GroupRepository {

    private val db = FirebaseFirestore.getInstance()
    private val groupsCollection = db.collection("groups")

    suspend fun addGroup(group: Group) {
        groupsCollection.document(group.groupId).set(group).await()
    }

    suspend fun getGroup(groupId: String): Group? {
        val document = groupsCollection.document(groupId).get().await()
        return document.toObject(Group::class.java)
    }

    suspend fun updateGroup(group: Group) {
        groupsCollection.document(group.groupId).set(group).await()
    }

    suspend fun deleteGroup(groupId: String) {
        groupsCollection.document(groupId).delete().await()
    }
}
