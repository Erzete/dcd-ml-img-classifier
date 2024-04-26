package com.dicoding.asclepius.view.news

import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.data.remote.retrofit.ApiConfig
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel() : ViewModel(){
    private val _listNews = MutableLiveData<List<ArticlesItem>>()
    val listNews : LiveData<List<ArticlesItem>> = _listNews

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getNews()
    }

    fun getNews() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getCancerNews("cancer", "health", "en")
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listNews.value = response.body()?.articles
                } else {
                    Log.e("Get News", "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Get News", "onFailure: ${t.message.toString()}")
            }
        })
    }
}