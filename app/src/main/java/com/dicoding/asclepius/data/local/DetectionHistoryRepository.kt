package com.dicoding.asclepius.data.local

import androidx.lifecycle.LiveData
import com.dicoding.asclepius.data.local.entity.DetectionHistory
import com.dicoding.asclepius.data.local.room.DetectionHistoryDao
import com.dicoding.asclepius.utils.AppExecutors

class DetectionHistoryRepository private constructor(
    private val detectionHistoryDao: DetectionHistoryDao,
    private val appExecutors: AppExecutors
) {

    fun getHistory(): LiveData<List<DetectionHistory>> {
        return detectionHistoryDao.getHistory()
    }

    fun insert(newHistory: DetectionHistory) {
        appExecutors.diskIO.execute { detectionHistoryDao.insert(newHistory) }
    }
    fun delete(newHistory: DetectionHistory) {
        appExecutors.diskIO.execute { detectionHistoryDao.delete(newHistory) }
    }

    companion object {
        @Volatile
        private var instance: DetectionHistoryRepository? = null
        fun getInstance(
            detectionHistoryDao: DetectionHistoryDao,
            appExecutors: AppExecutors
        ): DetectionHistoryRepository =
            instance ?: synchronized(this) {
                instance ?: DetectionHistoryRepository(detectionHistoryDao, appExecutors)
            }.also { instance = it }
    }
}