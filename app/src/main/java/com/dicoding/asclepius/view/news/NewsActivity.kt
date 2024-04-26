package com.dicoding.asclepius.view.news

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.databinding.ActivityNewsBinding
import com.dicoding.asclepius.view.ViewModelFactory

class NewsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNews.addItemDecoration(itemDecoration)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val newsViewModel: NewsViewModel by viewModels {
            factory
        }

        newsViewModel.listNews.observe(this) { newsList ->
            setNewsData(newsList)
        }

        newsViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setNewsData(newsList: List<ArticlesItem>) {
        val adapter = NewsAdapter()
        adapter.submitList(newsList)
        binding.rvNews.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}