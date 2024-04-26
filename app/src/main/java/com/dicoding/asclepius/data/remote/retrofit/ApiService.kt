package com.dicoding.asclepius.data.remote.retrofit

import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.data.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    fun getCancerNews(
        @Query("q") query: String,
        @Query("category") category: String,
        @Query("language") language: String,
    ): Call<NewsResponse>
}