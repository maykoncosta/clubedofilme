package com.example.clubedofilme.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clubedofilme.models.Group
import com.example.clubedofilme.repositories.GroupRepository
import kotlinx.coroutines.launch

class RecommendationsViewModel(private val groupRepository: GroupRepository) : ViewModel() {

    private val _groupData = MutableLiveData<Group?>()
    val groupData: MutableLiveData<Group?> get() = _groupData

    init {
        fetchGroupData()
    }

    private fun fetchGroupData() {
        viewModelScope.launch {
            val group = groupRepository.getGroup("groupId")
            _groupData.value = group
        }
    }
}
