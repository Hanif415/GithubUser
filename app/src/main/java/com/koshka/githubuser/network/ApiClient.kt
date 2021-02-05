package com.koshka.githubuser.network

import com.koshka.githubuser.model.ResponseUser
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiClient {
    companion object {
        const val URL = "https://api.github.com/"
        fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getService(): ApiInterface = getClient().create(ApiInterface::class.java)
    }
}

interface ApiInterface {
    @GET("search/users?q=sidiqpermana")
    fun getUser(): Call<ResponseUser>
}