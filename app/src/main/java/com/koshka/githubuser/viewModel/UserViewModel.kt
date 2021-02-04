package com.koshka.githubuser.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koshka.githubuser.model.ItemsItem

class UserViewModel : ViewModel() {
    val listUser = MutableLiveData<ArrayList<ItemsItem>>()

    fun setUser(userName: String) {

    }
}