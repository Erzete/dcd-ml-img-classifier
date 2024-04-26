package com.dicoding.asclepius.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.data.local.DetectionHistoryRepository
import com.dicoding.asclepius.di.Injection
import com.dicoding.asclepius.view.history.HistoryViewModel
import com.dicoding.asclepius.view.news.NewsViewModel

class ViewModelFactory private constructor(private val historyRepository: DetectionHistoryRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(historyRepository) as T
        } else if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}