package com.dicoding.asclepius.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.local.DetectionHistoryRepository
import com.dicoding.asclepius.data.local.entity.DetectionHistory

class HistoryViewModel(private val historyRepository: DetectionHistoryRepository) : ViewModel() {
    private val _historyList = MutableLiveData<List<DetectionHistory>>()
    val historyList : LiveData<List<DetectionHistory>> = _historyList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getHistory()
    }
    fun getHistory() {
        _isLoading.value = true
        historyRepository.getHistory().observeForever { newHistoryList ->
            _historyList.value = newHistoryList
            _isLoading.value = false
        }
    }

    fun addHistory(imageUri: String, label: String?, score: Int?) {
        val detection = DetectionHistory(imageUri, label, score)
        historyRepository.insert(detection)
    }

}