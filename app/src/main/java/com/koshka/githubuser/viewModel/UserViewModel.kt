package com.koshka.githubuser.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koshka.githubuser.model.ResponseUser
import com.koshka.githubuser.model.UsersItem
import com.koshka.githubuser.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class UserViewModel : ViewModel() {
    val listUser = MutableLiveData<List<UsersItem?>>()

    fun setUser(userName: String) {
        ApiClient.getService().getUser(ApiClient.SEARCH_URL + userName).enqueue(object : retrofit2.Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                val user: List<UsersItem?> = response.body()?.items as List<UsersItem?>
                listUser.postValue(user)
            }

            @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Log.d("onFailure : ", t.localizedMessage)
            }

        })
    }

    fun getUser(): MutableLiveData<List<UsersItem?>> {
        return listUser
    }
}