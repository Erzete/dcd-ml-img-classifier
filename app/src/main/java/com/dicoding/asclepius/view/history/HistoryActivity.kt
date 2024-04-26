package com.dicoding.asclepius.view.history

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.entity.DetectionHistory
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.view.ViewModelFactory

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvHistory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvHistory.addItemDecoration(itemDecoration)
        val itemAnimator = DefaultItemAnimator()
        binding.rvHistory.itemAnimator = itemAnimator

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val historyViewModel: HistoryViewModel by viewModels {
            factory
        }

        historyViewModel.historyList.observe(this) { newHistoryList ->
            setHistoryData(newHistoryList)
        }

        historyViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setHistoryData(newHistoryList: List<DetectionHistory>) {
        val adapter = HistoryAdapter()
        adapter.submitList(newHistoryList)
        binding.rvHistory.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}