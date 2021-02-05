package com.koshka.githubuser.network

import com.koshka.githubuser.model.ResponseUser
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

class ApiClient {
    companion object {
        private const val BASE_URL = "https://api.github.com/"
        const val SEARCH_URL = "search/users?q="
        fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getService(): ApiInterface = getClient().create(ApiInterface::class.java)
    }
}

interface ApiInterface {
    @GET
    fun getUser(@Url url: String): Call<ResponseUser>
}